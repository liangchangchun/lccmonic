package com.common.auth.manager;

import com.common.auth.exceptions.AuthException;
import com.common.auth.model.token.TokenItem;
import com.common.auth.utils.AuthUtil;
import com.common.enums.AppEnum;
import com.common.enums.auth.AuthCodeEnum;
import com.common.enums.auth.AuthLevelEnum;
import com.common.model.vo.auth.AccessToken;
import com.common.model.vo.auth.TokenModel;
import com.common.model.vo.auth.UserAuth;
import com.common.model.vo.auth.UserCache;
import com.common.model.vo.auth.login.MerchantUserInfo;
import com.common.model.vo.auth.login.UserInfo;
import com.common.utils.HCommonUtil;
import com.common.utils.redis.RedisUtils;
import com.xuanyan.hmc.midware.assist.utils.string.StringUtil;
import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <b>Description：商户端token 操作</b>  <br/>
 * <b>ClassName：</b> com.common.auth.manager <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2017/4/6 0006 <br/>
 * <b>@version: </b>  <br/>
 */
public class MPSTokenLogicTmpl extends AbstractTokenLogic{
    
    private Logger logger =LoggerFactory.getLogger(MPSTokenLogicTmpl.class);
    
    private static class SingletonHolder{
        private static final MPSTokenLogicTmpl INSTANCE=new MPSTokenLogicTmpl();
    }
    private MPSTokenLogicTmpl(){

    }
    public static final MPSTokenLogicTmpl getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public synchronized TokenModel createToken(UserInfo userInfo, String source) {
        String userId = userInfo.getLoginId();
        String registId=((MerchantUserInfo)userInfo).getRegistId();
        // redis中保存的key
        String userTokenKey = AuthUtil.getUserCachedKey(source, userId);
        
        // 从redis中获取usercache
        UserCache userCache=new UserCache();
        // 设置accessToken
        AccessToken accessToken = getAccessToken(userId, source,registId);
        List<AccessToken> accessTokens=new CopyOnWriteArrayList<>();
        accessTokens.add(accessToken);
        userCache.setAccessTokens(accessTokens);
        
        logger.info("debugat："+userId+"--"+accessToken.getAccessToken());
        // 设置当前用户信息
        UserInfo reUserInfo = putUser(userCache, userInfo);
        // 保存和更新redis缓存
        RedisUtils.save(userTokenKey, userCache, AuthLevelEnum.TOKEN_LOW.getValue());
        // 设置返回给前台的值
        UserAuth tokenResult = new UserAuth(accessToken.getAccessToken(), null,
                accessToken.getExpireIn(), 0);
        TokenModel ut = new TokenModel(tokenResult, reUserInfo);
        return ut;
    }

    public synchronized boolean validateAccessToken(String accessToken) {
        if (StringUtil.isEmpty(accessToken)) {
            throw new AuthException(AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getValue(),
                    AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getDesc());
        }
        UserCache userCache =  getUserCacheByToken(accessToken);
        if(userCache != null){
            List<AccessToken> accessTokens=userCache.getAccessTokens();
            AccessToken at=accessTokens.get(0);
            if(accessToken!=null&&at!=null&&accessToken.equals(at.getAccessToken())){
                try {
                    TokenItem tokenItem= AuthUtil.decryptToken(at.getAccessToken());
                    String source=tokenItem.getSource();
                    if(AppEnum.isMPS(source)&&(userCache.getUserInfo()) instanceof MerchantUserInfo){
                        return true;
                    }else{
                        logger.error("商户端token校验失败："+source+"，accessToken："+accessToken+"，at："+at.getAccessToken()); 
                    }
                }catch (Exception e){
                    logger.error(e);
                    e.printStackTrace();
                    throw new AuthException(AuthCodeEnum.MECHARNT_USER_DECRYPT_ACCESS_TOKEN_ERROR.getValue()
                            ,AuthCodeEnum.MECHARNT_USER_DECRYPT_ACCESS_TOKEN_ERROR.getDesc());
                }
            }else{
                logger.error("未获取到商户的token信息："+accessToken);
            }
        }{
            logger.error("未获取到商户信息："+accessToken);
        }
        return false;
    }

    private AccessToken getAccessToken(String userId, String source,String regedit) {
        String accessTokenValue=AuthUtil.genMPSAccessToken(userId,source,regedit);
        long expireInSecend = AuthLevelEnum.TOKEN_LOW.getValue();// 剩余秒数
        AccessToken accessToken = new AccessToken(accessTokenValue);
        accessToken.setExpireIn(expireInSecend);
        accessToken.setCreateTime(System.currentTimeMillis());
        return accessToken;
    }

    private UserInfo putUser(UserCache userCache, UserInfo userInfo) {
        MerchantUserInfo merchantUserInfo =(MerchantUserInfo)userInfo;
        userInfo.setLoginId(merchantUserInfo.getEmpId());
        userInfo.setLoginCode(merchantUserInfo.getEmpUser());
        userInfo.setLoginType(merchantUserInfo.getLoginType());
        userInfo.setLoginUserName(merchantUserInfo.getEmpName());
        userCache.setUserInfo(userInfo);
        return userInfo;
    }

}

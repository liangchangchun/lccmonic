package com.common.auth.manager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.base.Strings;
import com.common.auth.exceptions.AuthException;
import com.common.auth.model.token.TokenItem;
import com.common.auth.utils.AuthUtil;
import com.common.config.HConfig;
import com.common.enums.AppEnum;
import com.common.enums.auth.AuthCodeEnum;
import com.common.enums.auth.AuthLevelEnum;
import com.common.model.vo.auth.AccessToken;
import com.common.model.vo.auth.RefreshToken;
import com.common.model.vo.auth.TokenModel;
import com.common.model.vo.auth.UserAuth;
import com.common.model.vo.auth.UserCache;
import com.common.model.vo.auth.login.MemberUserInfo;
import com.common.model.vo.auth.login.MerchantUserInfo;
import com.common.model.vo.auth.login.OperatorUserInfo;
import com.common.model.vo.auth.login.UserInfo;
import com.common.utils.redis.RedisUtils;
import com.xuanyan.hmc.midware.assist.utils.date.DateUtil;
import com.xuanyan.hmc.midware.assist.utils.string.StringUtil;

/**
 * <b>Description：</b> token逻辑实现 <br/>
 * <b>ClassName：</b> TokenLogicImpl <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年7月27日 下午5:37:06 <br/>
 * <b>@version: </b> <br/>
 */
public class TokenLogicImpl extends AbstractTokenLogic implements TokenLogic {

    public static TokenLogic tokenLogic = new TokenLogicImpl();
    
    private static final String authExcludePhone=HConfig.SYSTEM.getProperty("hmc.auth.exclude.phone","13512341234");

    private TokenLogicImpl() {
    }

    public static TokenLogic getInstance() {
        return tokenLogic;
    }

    private static int allowTokenSize = 2000;

    @Override
    public TokenModel createToken(UserInfo userInfo, String source) {
        String userId = userInfo.getLoginId();
        // redis中保存的key
        String userTokenKey = AuthUtil.getUserCachedKey(source, userId);
        // 从redis中获取usercache
        UserCache userCache = RedisUtils.get(userTokenKey);
        if (userCache == null) {// 用户未登录过
            userCache = new UserCache();
        }
        // 设置当前用户信息
        UserInfo reUserInfo = putUser(userCache, userInfo);
        
        // 设置refreshToken
        RefreshToken refreToken = putRefreshToken(userCache, userId, source);
        // 设置accessToken
        AccessToken accessToken = putAccessToken(userCache, userId, source);
        
        // 保存和更新redis缓存
        RedisUtils.save(userTokenKey, userCache, AuthLevelEnum.TOKEN_MAX_LOW.getValue());
        // 保存和更新 微信openID 与 用户 userId 缓存的 userTokenKey
        String weixinCacheValue = userId;
        RedisUtils.save(AuthUtil.getWeixinUserCachedKey(userInfo.getWxOpenId()),weixinCacheValue);
        
        // 设置返回给前台的值
        UserAuth tokenResult = new UserAuth(accessToken.getAccessToken(), refreToken.getRefreshToken(),
                accessToken.getExpireIn(), refreToken.getExpireIn());
        TokenModel ut = new TokenModel(tokenResult, reUserInfo);
        return ut;
    }

    @Override
    public UserInfo getUserInfoById(String userId, String source) {

        UserCache userCache =getUserCacheByUserId(userId, source);
        return userCache==null?null:userCache.getUserInfo();
    }

    @Override
    public UserInfo getUserInfoByToken(String accessToken) {
        UserCache userCache = getUserCacheByToken(accessToken);
        return userCache==null?null:userCache.getUserInfo();
    }

    /**
     * 校验accessToken
     */
   public  synchronized boolean validateAccessToken(String accessToken) {
       if (StringUtil.isEmpty(accessToken)) {
           throw new AuthException(AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getValue(),
                   AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getDesc());
       }
       UserCache userCache =  getUserCacheByToken(accessToken);
       if(userCache == null){
           return false;
       }
       if(!(tokenLogic.getUserCacheByToken(accessToken).getUserInfo() instanceof MemberUserInfo)){
           return false;
       }
       long expireInSecend = AuthLevelEnum.TOKEN_HIGH.getValue();// 剩余秒数
       boolean currentTokenFlag = false;
       
       List<AccessToken> accessTokenList = userCache.getAccessTokens();
       if (accessTokenList != null && !accessTokenList.isEmpty()) {
           for (AccessToken at : accessTokenList) {
               long expireTime = at.getCreateTime() + (expireInSecend * 1000l);
               long currentTime = DateUtil.getCurrentDateMilliSecond();
               if(accessToken.equals(at.getAccessToken())){//先验证当前token是否过期
                   if (expireTime < currentTime) {// 已失效
                       accessTokenList.remove(at);
                       currentTokenFlag =false;
                   } else {
                       currentTokenFlag =true;
                   }
               }else { //轮询其他token中过期的清空掉
                   if (expireTime < currentTime) {// 已失效
                       accessTokenList.remove(at);
                   } else {
                       continue;
                   }
               }
           }
       } else{
           return false;
       }
       
       if(currentTokenFlag){//当前token未失效
           return true;
       }
        
        return false;
    }
   /**
    * 校验refreshToken
    */
   public synchronized boolean validateRefreshToken(String refreshToken) {
       
       if (StringUtil.isEmpty(refreshToken)) {
           throw new AuthException(AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getValue(),
                   AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getDesc());
       }
       UserCache userCache = getUserCacheByToken(refreshToken);
       if(userCache == null){
           return false;
       }
       long expireInSecend = AuthLevelEnum.TOKEN_LOW.getValue();// 剩余秒数
       boolean currentTokenFlag = false;
       
       List<RefreshToken> refreshTokenList = userCache.getRefreshTokens();
       if (refreshTokenList != null && !refreshTokenList.isEmpty()) {
           for (RefreshToken at : refreshTokenList) {
               long expireTime = at.getCreateTime() + (expireInSecend * 1000l);
               long currentTime = DateUtil.getCurrentDateMilliSecond();
               if(refreshToken.equals(at.getRefreshToken())){//先验证当前token是否过期
                   if (expireTime < currentTime) {// 已失效
                       refreshTokenList.remove(at);
                       currentTokenFlag =false;
                   } else {
                       currentTokenFlag =true;
                   }
               }else { //轮询其他token中过期的清空掉
                   if (expireTime < currentTime) {// 已失效
                       refreshTokenList.remove(at);
                   } else {
                       continue;
                   }
               }
           }
       } else{
           return false;
       }
       
       if(currentTokenFlag){//当前token有效
           return true;
       }
        
        return false;
   }

    @Override
    public UserAuth getUserAuthByRefreToken(String refreshToken) {
        
        if(validateRefreshToken(refreshToken)){
          TokenItem  tokenItem = AuthUtil.decryptToken(refreshToken);
          String userTokenKey = AuthUtil.getUserCachedKey(tokenItem.getSource(),tokenItem.getUserId() );
          // 从redis中获取usercache
          UserCache userCache = getUserCacheByToken(refreshToken);
          if (userCache == null) {// 用户未登录过
              userCache = new UserCache();
          }
          //删除原始的refreshToken,生成新的accessToken和refreshToken TODO
          
          // 设置refreshToken
          RefreshToken refreToken = putRefreshToken(userCache, tokenItem.getUserId(), tokenItem.getSource());
          // 设置accessToken
          AccessToken accessToken = putAccessToken(userCache, tokenItem.getUserId(), tokenItem.getSource());
          // 保存和更新redis缓存
          RedisUtils.save(userTokenKey, userCache, AuthLevelEnum.TOKEN_MAX_LOW.getValue());
          // 设置返回给前台的值
          UserAuth tokenResult = new UserAuth(accessToken.getAccessToken(), refreToken.getRefreshToken(),
                  accessToken.getExpireIn(), refreToken.getExpireIn());
          return tokenResult;
        }else {
            throw new AuthException(AuthCodeEnum.USER_REFRESH_TOKEN_ERROR.getValue(),
                    AuthCodeEnum.USER_REFRESH_TOKEN_ERROR.getDesc());
        }
    }
    
    @Override
    public UserCache getUserCacheByUserId(String userId) {
        
        return getUserCacheByUserId(userId,AppEnum.HMC_PORTAL_PC.getValue());
    }


    @Override
    public void deleteUserCache(String userId) {
//        Set<String> keys =RedisUtils.matchKeys("*"+userId);
//        if(keys!=null && !keys.isEmpty()){
//            for(String key : keys){
//                RedisUtils.del(key);
//            }
//        }
        
    }

    
   
    @Override
    public void deleteUserToken(String accessToken, String refreshToken) {
        UserCache userCache =  getUserCacheByToken(accessToken);
        if(userCache == null){
            return ;
        }
        
        List<AccessToken> accessTokens=userCache.getAccessTokens();
        List<RefreshToken> refreshTokens=userCache.getRefreshTokens();
        if(StringUtil.isNotEmpty(accessToken)){
            if (accessTokens != null && !accessTokens.isEmpty()) {
                for (AccessToken at : accessTokens) {
                    long expireTime = at.getCreateTime() + (AuthLevelEnum.TOKEN_HIGH.getValue() * 1000);
                    long currentTime = DateUtil.getCurrentDateMilliSecond();
                    if(accessToken.equals(at.getAccessToken())){//先验证当前token是否过期
                        accessTokens.remove(at);
                    }else { //轮询其他token中过期的清空掉
                        if (expireTime < currentTime) {// 已失效
                            refreshTokens.remove(at);
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
         if(StringUtil.isNotEmpty(refreshToken)){
             if (refreshTokens != null && !refreshTokens.isEmpty()) {
                 for (RefreshToken at : refreshTokens) {
                     long expireTime = at.getCreateTime() + (AuthLevelEnum.TOKEN_LOW.getValue() * 1000);
                     long currentTime = DateUtil.getCurrentDateMilliSecond();
                     if(refreshToken.equals(at.getRefreshToken())){//先验证当前token是否过期
                         refreshTokens.remove(at);
                     }else { //轮询其他token中过期的清空掉
                         if (expireTime < currentTime) {// 已失效
                             refreshTokens.remove(at);
                         } else {
                             continue;
                         }
                     }
                 }
             }
        }
        userCache.setAccessTokens(accessTokens);
        userCache.setRefreshTokens(refreshTokens);
        RedisUtils.save(userCache.getUserCacheKey(), userCache,AuthLevelEnum.TOKEN_MAX_LOW.getValue());
    }
    
    
    /**
     * 向userCache中设置不同的用户
     * putUser <br/> 
     * @param userCache
     * @param userInfo
     * @return  UserInfo <br/>
     */
    private UserInfo putUser(UserCache userCache, UserInfo userInfo) {
      //  UserInfo userInfo1 = new UserInfo();
        // 会员用户信息
        if (userInfo instanceof MemberUserInfo) {
            MemberUserInfo memberUserInfo = (MemberUserInfo) userInfo;
            // userInfo1.setClientIp(clientIp);
            userInfo.setLoginId(memberUserInfo.getUserId());
            userInfo.setLoginCode(memberUserInfo.getUserPhone());
            userInfo.setLoginType(memberUserInfo.getUserType());
            userInfo.setLoginUserName(memberUserInfo.getUserName());
        } // 运营用户信息
        else if (userInfo instanceof OperatorUserInfo) {
            OperatorUserInfo operatorUserInfo =(OperatorUserInfo)userInfo;
            // userInfo1.setClientIp(clientIp);
            userInfo.setLoginId(operatorUserInfo.getUserId());
            userInfo.setLoginCode(operatorUserInfo.getUserCode());
            userInfo.setLoginType(operatorUserInfo.getLoginType());
            userInfo.setLoginUserName(operatorUserInfo.getUserName());
        } // 商户用户信息
        else if (userInfo instanceof MerchantUserInfo) {
            MerchantUserInfo merchantUserInfo =(MerchantUserInfo)userInfo;
            // userInfo1.setClientIp(clientIp);
            userInfo.setLoginId(merchantUserInfo.getEmpId());
            userInfo.setLoginCode(merchantUserInfo.getEmpUser());
            userInfo.setLoginType(merchantUserInfo.getLoginType());
            userInfo.setLoginUserName(merchantUserInfo.getEmpName());
        }
        userCache.setUserInfo(userInfo);
        return userInfo;
    }
    
    
    /**
     * 校验refreshToken checkRefreshToken <br/>
     * 
     * @param token
     * @param userCache
     * @return boolean <br/>
     */
    private RefreshToken putRefreshToken(UserCache userCache, String userId, String source) {
        String refreshTokenValue = AuthUtil.genRefreshToken(userId, source);
        long expireInSecend = AuthLevelEnum.TOKEN_LOW.getValue();// 剩余秒数
        // 当前的refreshToken
        RefreshToken refreshToken = new RefreshToken(refreshTokenValue);
        refreshToken.setExpireIn(expireInSecend);

        List<RefreshToken> refreshTokenList = userCache.getRefreshTokens();
        UserInfo userInfo=userCache.getUserInfo();
        if (refreshTokenList != null && !refreshTokenList.isEmpty()) {
	        if (!authExcludePhone.contains(userInfo.getLoginCode())) {
	            if (refreshTokenList.size() > allowTokenSize) {
	            	throw new AuthException(AuthCodeEnum.AUTH_LOGIN_TOO_MANY.getValue(),
	                        AuthCodeEnum.AUTH_LOGIN_TOO_MANY.getDesc());
	            }
	          }

            for (RefreshToken rf : refreshTokenList) {
                long expireTime = rf.getCreateTime() + (expireInSecend * 1000l);
                long currentTime = DateUtil.getCurrentDateMilliSecond();
                if (expireTime < currentTime) {// 已失效
                    refreshTokenList.remove(rf);
                } else {
                    continue;
                }
            }
            // 新增refreshToken
            refreshTokenList.add(refreshToken);
        } else {
            refreshTokenList = new CopyOnWriteArrayList<RefreshToken>();
            refreshTokenList.add(refreshToken);
        }
        userCache.setRefreshTokens(refreshTokenList);
        return refreshToken;
    }

    /**
     * 校验accessToken checkRefreshToken <br/>
     * 
     * @param token
     * @param userCache
     * @return boolean <br/>
     */
    private AccessToken putAccessToken(UserCache userCache, String userId, String source) {
        String accessTokenValue = AuthUtil.genAccessToken(userId, source);
        long expireInSecend = AuthLevelEnum.TOKEN_HIGH.getValue();// 剩余秒数

        // 当前的accessToken
        AccessToken accessToken = new AccessToken(accessTokenValue);
        accessToken.setExpireIn(expireInSecend);

        List<AccessToken> accessTokenList = userCache.getAccessTokens();
        UserInfo userInfo=userCache.getUserInfo();
        if (accessTokenList != null && !accessTokenList.isEmpty()) {
        	if (!authExcludePhone.contains(userInfo.getLoginCode())) {
        		if (accessTokenList.size() > allowTokenSize) {
        			throw new AuthException(AuthCodeEnum.AUTH_LOGIN_TOO_MANY.getValue(),
        					AuthCodeEnum.AUTH_LOGIN_TOO_MANY.getDesc());
        		}
        	}

            for (AccessToken at : accessTokenList) {
                long expireTime = at.getCreateTime() + (expireInSecend * 1000l);
                long currentTime = DateUtil.getCurrentDateMilliSecond();
                if (expireTime < currentTime) {// 已失效
                    accessTokenList.remove(at);
                } else {
                    continue;
                }
            }
            // 新增accessToken
            accessTokenList.add(accessToken);
        } else {
            accessTokenList = new CopyOnWriteArrayList<AccessToken>();
            accessTokenList.add(accessToken);
        }
        userCache.setAccessTokens(accessTokenList);
        return accessToken;
    }
}

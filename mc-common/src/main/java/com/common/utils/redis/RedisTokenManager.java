package com.common.utils.redis;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.common.config.HConfig;
import com.common.enums.auth.AuthLevelEnum;
import com.common.model.vo.auth.AccessToken;
import com.common.model.vo.auth.DecryptToken;
import com.common.model.vo.auth.RefreshToken;
import com.common.model.vo.auth.TokenModel;
import com.common.model.vo.auth.UserAuth;
import com.common.model.vo.auth.UserCache;
import com.common.model.vo.auth.login.MemberUserInfo;
import com.common.model.vo.auth.login.OperatorUserInfo;
import com.common.model.vo.auth.login.UserInfo;
import com.common.utils.HCommonUtil;
import com.common.utils.redis.RedisUtils;

public class RedisTokenManager{
	
	private final long tokenSize=HConfig.CONTEXT.getProperty("token.size", 300);
	/**     
	 * 创建token <br/> 
	 * createToken <br/> 
	 * @param userInfo
	 * @param source
	 * @param ip
	 * @param applicationPrefix
	 * @return  TokenModel <br/>   
	 */
	public  TokenModel createToken(UserInfo userInfo,String source,String clientIp,String applicationPrefix) {
		long timeStamp=System.currentTimeMillis();
		String userId=userInfo.getLoginId();
		String loginType=userInfo.getLoginType();
		String accessTokenValue=HCommonUtil.createAccessToken(userId, loginType, source, clientIp, applicationPrefix);
        String refreshTokenValue=HCommonUtil.createRefreshToken(userId, loginType, source, clientIp, applicationPrefix,timeStamp);
        long token_create_time=System.currentTimeMillis();
        AccessToken accessToken=new AccessToken(accessTokenValue,token_create_time);
        RefreshToken refreshToken=new RefreshToken(refreshTokenValue,token_create_time);
        List<AccessToken> accessTokens;
        List<RefreshToken> refreshTokens;
        UserCache userCache=RedisUtils.get(applicationPrefix+userId);
        if(userCache!=null){
        	accessTokens=userCache.getAccessTokens();
        	refreshTokens=userCache.getRefreshTokens();
        	//限制accessToken和refreshToken的大小
        	if(accessTokens.size()>=tokenSize||refreshTokens.size()>=tokenSize){
        		return null;
        	}
        	//遍历token是否过期
        	/*for(AccessToken item:accessTokens){
        		checkToken(item.getAccessToken(),true,false);
        	}
        	for(RefreshToken item1:refreshTokens){
        		checkToken(item1.getRefreshToken(),false,false);
        	}*/
        }else{
        	accessTokens=new CopyOnWriteArrayList<AccessToken>();
        	refreshTokens=new CopyOnWriteArrayList<RefreshToken>();
        }
        accessTokens.add(accessToken);
    	refreshTokens.add(refreshToken);
    	userCache=new UserCache(accessTokens,refreshTokens,userInfo);
        RedisUtils.save(applicationPrefix+userId, userCache,AuthLevelEnum.TOKEN_MAX_LOW.getValue());
        UserAuth userAuth=new UserAuth(accessTokenValue,refreshTokenValue,AuthLevelEnum.TOKEN_HIGH.getValue(),AuthLevelEnum.TOKEN_LOW.getValue());
        UserInfo userInfo1=new UserInfo();
        //会员用户信息
        if(userInfo instanceof MemberUserInfo){
        	MemberUserInfo memberUserInfo=(MemberUserInfo)userInfo;
            userInfo1.setClientIp(clientIp);
            userInfo1.setLoginId(memberUserInfo.getUserId());
            userInfo1.setLoginCode(memberUserInfo.getUserPhone());
            userInfo1.setLoginType(memberUserInfo.getLoginType());
            userInfo1.setLoginUserName(userInfo.getLoginUserName());
        }//运营用户信息
        else if(userInfo instanceof OperatorUserInfo){
        	OperatorUserInfo operatorUserInfo=new OperatorUserInfo();
        	userInfo1.setClientIp(clientIp);
        	userInfo1.setLoginId(operatorUserInfo.getUserId());
        	userInfo1.setLoginCode(operatorUserInfo.getUserCode());
        	userInfo1.setLoginType(operatorUserInfo.getLoginType());
        	userInfo1.setLoginUserName(operatorUserInfo.getUserName());
        }/*//交易用户信息
        else if(userInfo instanceof MerchantUserInfo){
        	MerchantUserInfo merchantUserInfo=new MerchantUserInfo();
        	userInfo1.setClientIp(clientIp);
        	userInfo1.setLoginId(merchantUserInfo.getEmpId());
        	userInfo1.setLoginCode(merchantUserInfo.getEmpUser());
        	userInfo1.setLoginType(merchantUserInfo.getLoginType());
        	userInfo1.setLoginUserName(merchantUserInfo.getEmpName());
        }*/else{
        	userInfo1=userInfo;
        	userInfo1.setClientIp(clientIp);
        }
        
        TokenModel tokenModel=new TokenModel(userAuth,userInfo1);
        return tokenModel;
	}

	/**     
	 * 检测token是否有效 <br/> 
	 * checkToken <br/> 
	 * @param token
	 * @param isAccessToken accessToken为true,refreshToken为false
	 * @param expandTimeFlag  是否重置过期时间
	 * @return  boolean <br/>   
	 */
	public synchronized boolean  checkToken(String token,boolean isAccessToken,boolean expandTimeFlag) {
		DecryptToken decryptToken=HCommonUtil.decryptToken(token);
		String userId=decryptToken.getUserId();
		String applicationPrefix=decryptToken.getApplicationPrefix();
		boolean flag=false;
		UserCache userTokenDetail=RedisUtils.get(applicationPrefix+userId);
		List<AccessToken> accessTokens;
	    List<RefreshToken> refreshTokens;
		if(userTokenDetail!=null){
			if(isAccessToken){
				accessTokens=userTokenDetail.getAccessTokens();
				Iterator<AccessToken> iterator=accessTokens.iterator();
				while(iterator.hasNext()){
					AccessToken accessToken=iterator.next();
					String tokenValue=accessToken.getAccessToken();
					long expireTime=accessToken.getCreateTime()+AuthLevelEnum.TOKEN_HIGH.getValue()*1000l;
					long currentTime=System.currentTimeMillis();

					if(tokenValue.equals(token)){
						if(expireTime>currentTime){
							//重新设置accessToken过期时间
							if(expandTimeFlag){//如果是登录来的校验，就不重新设置创建时间
								accessToken.setCreateTime(currentTime);
							}
							flag=true;
						}else{
							accessTokens.remove(accessToken);
						}
					}else{
						if(expireTime<=currentTime){
							accessTokens.remove(accessToken);
						}
					}
				}
			}else{
				refreshTokens=userTokenDetail.getRefreshTokens();
				Iterator<RefreshToken> iterator=refreshTokens.iterator();
				while(iterator.hasNext()){
					RefreshToken refreshToken=iterator.next();
					String tokenValue=refreshToken.getRefreshToken();
					long expireTime=refreshToken.getCreateTime()+AuthLevelEnum.TOKEN_LOW.getValue()*1000l;
					long currentTime=System.currentTimeMillis();
					if(tokenValue.equals(token)){
						if(expireTime>currentTime){
							//生成了新的accessToken和refreshToken,并删除redis中refreshToken
							refreshTokens.remove(refreshToken);
							flag=true;
						}else{
							refreshTokens.remove(refreshToken);
						}
					}else{
						if(expireTime<=currentTime){
							refreshTokens.remove(refreshToken);
						}
					}
				}
			}
		}
        RedisUtils.save(applicationPrefix+userId, userTokenDetail,AuthLevelEnum.TOKEN_MAX_LOW.getValue());
		return flag;
	}

	/**     
	 * 根据token获取userDetail <br/> 
	 * getUserAuthByToken <br/> 
	 * @param token
	 * @param isAccessToken
	 * @return  UserAuth <br/>   
	 */
	public UserInfo getUserInfoByToken(String token) {
		DecryptToken decryptToken=HCommonUtil.decryptToken(token);
		String userId=decryptToken.getUserId();
		String applicationPrefix=decryptToken.getApplicationPrefix();
		UserCache userDetail=RedisUtils.get(applicationPrefix+userId);
		if(userDetail!=null){
			UserInfo loginInfo=userDetail.getUserInfo();
			return loginInfo;
		}
		return null;
	}

	/**     
	 * 删除token <br/> 
	 * deleteToken <br/> 
	 * @param token <br/>
	 */
	public boolean deleteToken(String token,boolean isAccessToken) {
		DecryptToken decryptToken=HCommonUtil.decryptToken(token);
		String userId=decryptToken.getUserId();
		String applicationPrefix=decryptToken.getApplicationPrefix();
		UserCache userCache=RedisUtils.get(applicationPrefix+userId);
		if(userCache!=null){
        	List<AccessToken> accessTokens=userCache.getAccessTokens();
        	List<RefreshToken> refreshTokens=userCache.getRefreshTokens();
        	if(isAccessToken){
        		accessTokens.remove(token);
        	}else{
        		refreshTokens.remove(token);
        	}
        	userCache.setAccessTokens(accessTokens);
        	userCache.setRefreshTokens(refreshTokens);
            RedisUtils.save(applicationPrefix+userId, userCache,AuthLevelEnum.TOKEN_MAX_LOW.getValue());
            return true;
        }
		return false;
	}

}

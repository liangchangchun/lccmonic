package com.common.auth.manager;

import com.common.model.vo.auth.TokenModel;
import com.common.model.vo.auth.UserAuth;
import com.common.model.vo.auth.UserCache;
import com.common.model.vo.auth.login.UserInfo;

/**
 * <b>Description：</b> token的逻辑 <br/>
 * <b>ClassName：</b> UserAuthLogic <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年7月27日 下午6:45:13 <br/>
 * <b>@version: </b>  <br/>
 */
public interface TokenLogic {
    
    /**
     * 创建token
     * createToken <br/> 
     * @param userInfo
     * @param source
     * @return  TokenModel <br/>
     */
    TokenModel createToken(UserInfo userInfo,String source);
    /**
     * 验证accessToken
     */
    boolean validateAccessToken(String accessToken);
    /**
     * 验证refreshToken
     */
    boolean validateRefreshToken(String refreshToken);
    /**
     * 根据refreshToken 获取 accessToken和refreshToken
     */
    UserAuth  getUserAuthByRefreToken(String refreshToken);
    /**
     * 根据用户ID和来源获取缓存用户
     * getUserCacheByUserId <br/> 
     * @param userId
     * @param source
     * @return  UserCache <br/>
     */
    UserCache getUserCacheByUserId(String userId,String source);
    /**
     * 根据用户Id获取缓存用户和token
     * 默认获取会员用户的userCache
     */
    UserCache getUserCacheByUserId(String userId);
    /**
     * 根据accessToken获取缓存用户和token
     */
    UserCache getUserCacheByToken(String token);
    
    /**
     * 根据用户Id获取当前用户
     */
    UserInfo getUserInfoById(String userId,String source);
    
    /**
     * 根据token获取当前用户
     */
    UserInfo getUserInfoByToken(String accessToken);
    
    /**
     * 根据用户Id删除缓存
     */
    void deleteUserCache(String userId);
    
    /**
     * 删除accesstoken和refreshtoken
     */
    void deleteUserToken(String accessToken,String refreshToken);
    
}

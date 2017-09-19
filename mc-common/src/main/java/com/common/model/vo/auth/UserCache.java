 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.model.vo.auth;

import java.io.Serializable;
import java.util.List;

import com.common.model.vo.auth.login.UserInfo;
/**
 * 
 * <b>Description：</b> redis中存储的用户信息 <br/>
 * <b>ClassName：</b> UserDetail <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年8月8日 下午2:15:03 <br/>
 * <b>@version: </b>  <br/>
 */
public class UserCache implements Cloneable, Serializable{

	private static final long serialVersionUID = 2979634546344040619L;
	/**
	 * redis中保存的key
	 */
	private String userCacheKey;
	/**
	 * refresh TokenList
	 */
	private List<RefreshToken> refreshTokens;
	/**
     * access TokenList
     */
	private List<AccessToken> accessTokens;
	
	/**
	 * user store
	 */
	private UserInfo loginInfo;


    /**
     * userCacheKey
     *
     * @return  the userCacheKey
     */
    
    public String getUserCacheKey() {
        return userCacheKey;
    }

    /**
     * @param userCacheKey the userCacheKey to set
     */
    public void setUserCacheKey(String userCacheKey) {
        this.userCacheKey = userCacheKey;
    }

    public UserCache() {
		super();
	}

	public UserCache(List<AccessToken> accessTokens, List<RefreshToken> refreshTokens, UserInfo userInfo) {
		super();
		this.accessTokens = accessTokens;
		this.refreshTokens = refreshTokens;
		this.loginInfo = userInfo;
	}

	public List<AccessToken> getAccessTokens() {
		return accessTokens;
	}

	public void setAccessTokens(List<AccessToken> accessTokens) {
		this.accessTokens = accessTokens;
	}

	public List<RefreshToken> getRefreshTokens() {
		return refreshTokens;
	}

	public void setRefreshTokens(List<RefreshToken> refreshTokens) {
		this.refreshTokens = refreshTokens;
	}

	public UserInfo getUserInfo() {
		return loginInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.loginInfo = userInfo;
	}

	
}

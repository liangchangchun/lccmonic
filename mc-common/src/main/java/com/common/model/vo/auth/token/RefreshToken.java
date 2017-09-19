 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.model.vo.auth.token;

import java.io.Serializable;
/**
 * 
 * <b>Description：</b> refresh token 用来产生 access token的<br/>
 * <b>ClassName：</b> RefreshToken <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月29日 上午11:24:27 <br/>
 * <b>@version: </b>  <br/>
 */
public class RefreshToken implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * refreshTokens
     */
    private String refreshToken;

    /**
     * 创建时间，缓存失效机制用此时间来实现
     */
    private long createTime;

    public RefreshToken() {
		super();
	}

	public RefreshToken(String refreshToken, long createTime) {
		super();
		this.refreshToken = refreshToken;
		this.createTime = createTime;
	}

	/**
     * refreshToken
     *
     * @return  the refreshToken
     */
    
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken the refreshToken to set
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * createTime
     *
     * @return  the createTime
     */
    
    public long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    
    
}

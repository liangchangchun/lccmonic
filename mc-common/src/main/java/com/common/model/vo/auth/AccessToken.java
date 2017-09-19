/**
* 上海轩言网络信息科技有限公司
* Copyright (c) 2016, xuanyan All Rights Reserved.
*/
package com.common.model.vo.auth;

import java.io.Serializable;

import com.xuanyan.hmc.midware.assist.utils.date.DateUtil;

/**
 * 
 * <b>Description：</b> AccessToken token 授权客户端进行认证的token<br/>
 * <b>ClassName：</b> RefreshToken <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月29日 上午11:24:27 <br/>
 * <b>@version: </b> <br/>
 */
public class AccessToken implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 创建时间，缓存失效机制用此时间来实现
     */
    private long createTime;

    /**
     * token过期剩余时间
     */
    private long expireIn;

    public AccessToken() {
        super();
    }

    public AccessToken(String accessToken, long createTime) {
        super();
        this.accessToken = accessToken;
        this.createTime = createTime;
    }

    public AccessToken(String accessToken) {
        super();
        this.accessToken = accessToken;
        this.createTime = DateUtil.getCurrentDateMilliSecond();
    }

    public long getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(long expireIn) {
		this.expireIn = expireIn;
	}

	/**
     * accessToken
     *
     * @return the accessToken
     */

    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken
     *            the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * createTime
     *
     * @return the createTime
     */

    public long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

}

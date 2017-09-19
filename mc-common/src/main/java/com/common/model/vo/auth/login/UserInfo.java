 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.model.vo.auth.login;

import java.io.Serializable;


 /**
 * <b>Description：</b> 存储的用户基本信息  <br/>
 * <b>ClassName：</b> UserInfo <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年8月1日 下午8:07:45 <br/>
 * <b>@version: </b>  <br/>
 */
public class UserInfo implements Serializable{
	
    private static final long serialVersionUID = -5363025942526212751L;
    /**
     * 登录用户Id
     */
    private String loginId;
    /**
     * 微信登录标识
     */
    private String wxOpenId;
	/**
	 * 登录用户标识，手机号|用户名|email
	 */
	private String loginCode;
	
	/**
     * 登录用户姓名
     */
    private String loginUserName;
	
	/**
	 * 登录用户类型，会员用户|商户|运营用户
	 */
	private String loginType;
	
	/**
	 * clientIp:客户端ip
	 */
	private String clientIp;
	/**
     * loginCode
     *
     * @return  the loginCode
     */
    
    public String getLoginCode() {
        return loginCode;
    }


    /**
     * @param loginCode the loginCode to set
     */
    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    /**
     * loginUserName
     *
     * @return  the loginUserName
     */
    
    public String getLoginUserName() {
        return loginUserName;
    }


    /**
     * @param loginUserName the loginUserName to set
     */
    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }


    public UserInfo() {
		
	}

    public UserInfo(String loginId, String loginCode, String loginUserName, String loginType) {
		super();
		this.loginId = loginId;
		this.loginCode = loginCode;
		this.loginUserName = loginUserName;
		this.loginType = loginType;
	}


	/**
     * loginId
     *
     * @return  the loginId
     */
    
    public String getLoginId() {
        return loginId;
    }


    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * loginType
     *
     * @return  the loginType
     */
    
    public String getLoginType() {
        return loginType;
    }


    /**
     * @param loginType the loginType to set
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }


	public String getClientIp() {
		return clientIp;
	}


	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}


	public String getWxOpenId() {
		return wxOpenId;
	}


	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	
}


 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.model.vo.auth;

import java.io.Serializable;

import com.common.utils.rsa.Encrypter;
import com.common.utils.string.StringUtil;

/**
 * <b>Description：</b> 用户请求认证信息 <br/>
 * <b>ClassName：</b> UserAuth <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月28日 下午4:25:29 <br/>
 * <b>@version: </b>  <br/>
 */
public class UserAuth implements Cloneable, Serializable{

	
	private static final long serialVersionUID = 1L;
	
    private String accessToken;

    private String refreshToken;
    
    private long accessToken_expires_in;
    
    private long refreshToken_expires_in;
    
    public UserAuth(){
    	
    }

    public UserAuth(String accessToken, String refreshToken, long accessToken_expires_in,long refreshToken_expires_in) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.accessToken_expires_in = accessToken_expires_in;
		this.refreshToken_expires_in=refreshToken_expires_in;
	}

	/**     
     * 根据accessToken或者refreshToken获取userId <br/> 
     * getUserId <br/> 
     * @param authentication
     * @return  String <br/>   
     */
    public  String getUserId(String authentication) {
    	if(StringUtil.isNotNull(authentication)){
    		authentication=Encrypter.decrypt(authentication);
    		String[] authArray=authentication.split("-");
    		return authArray[0];
    	}
    	return null;
    }

	

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getAccessToken_expires_in() {
		return accessToken_expires_in;
	}

	public void setAccessToken_expires_in(long accessToken_expires_in) {
		this.accessToken_expires_in = accessToken_expires_in;
	}

	public long getRefreshToken_expires_in() {
		return refreshToken_expires_in;
	}

	public void setRefreshToken_expires_in(long refreshToken_expires_in) {
		this.refreshToken_expires_in = refreshToken_expires_in;
	}




}

package com.common.model.vo.auth;

import java.io.Serializable;

import com.common.model.vo.auth.login.UserInfo;


 /**
 * <b>Description：</b> 生成返回给客户端数据 <br/>
 * <b>ClassName：</b> TokenModel <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年8月1日 下午3:21:47 <br/>
 * <b>@version: </b>  <br/>
 */
public class TokenModel implements Cloneable, Serializable{

	private static final long serialVersionUID = -6259781010970713345L;

	private UserAuth auth;
	
	private UserInfo loginInfo;

	
	public TokenModel() {
		super();
	}


	public TokenModel(UserAuth auth, UserInfo userInfo) {
		super();
		this.auth = auth;
		this.loginInfo = userInfo;
	}


	public UserAuth getAuth() {
		return auth;
	}


	public void setAuth(UserAuth auth) {
		this.auth = auth;
	}


	public UserInfo getUserInfo() {
		return loginInfo;
	}


	public void setUserInfo(UserInfo userInfo) {
		this.loginInfo = userInfo;
	}
	
	
}

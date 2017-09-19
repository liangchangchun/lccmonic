 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.model.vo.auth.login;

import java.io.Serializable;
import java.util.Date;


 /**
 * <b>Description：</b> 存储的用户基本信息  <br/>
 * <b>ClassName：</b> UserInfo <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年8月1日 下午8:07:45 <br/>
 * <b>@version: </b>  <br/>
 */
public class OperatorUserInfo extends UserInfo  implements Serializable{
	
    private static final long serialVersionUID = -5363025942526212751L;
    
    private String userId;

    private String userCode;

    private String userName;

    private String userPassword;

    private String userPhone;

    private String userDesc;

    private Date updateTime;

    private String updateUser;

    private String dataState;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}
    
    
    
}

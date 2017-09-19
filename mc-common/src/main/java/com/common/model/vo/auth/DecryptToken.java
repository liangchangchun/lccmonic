package com.common.model.vo.auth;

public class DecryptToken {

	private String userId;
	private String userType;
	private String source;
	private String ip;
	private String applicationPrefix;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getApplicationPrefix() {
		return applicationPrefix;
	}
	public void setApplicationPrefix(String applicationPrefix) {
		this.applicationPrefix = applicationPrefix;
	}
	
	
}

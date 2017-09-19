 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.model.vo.auth.login;

import java.io.Serializable;
import java.util.Date;


 /**
 * <b>Description：</b> 会员用户信息  <br/>
 * <b>ClassName：</b> UserInfo <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年8月1日 下午8:07:45 <br/>
 * <b>@version: </b>  <br/>
 */
public class MemberUserInfo extends UserInfo   implements Serializable{
	
    private static final long serialVersionUID = -5363025942526212751L;
    
    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 手机归属地
     */
    private String userLocation;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 询价次数
     */
    private Integer askCount;

    /**
     * 成交次数
     */
    private Integer dealCount;

    /**
     * 违约次数
     */
    private Integer breakCount;

    /**
     * 数据状态
     */
    private String dataState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 专属优惠码
     */
    private String userVldcode;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户等级
     */
    private String userLevel;

    /**
     * 用户状态
     */
    private String userState;

    /**
     * 用户账户余额
     */
    private Float userAccount;
    
    /**
     * 城市编码
     */
    private String cityCode;
    
    

    public MemberUserInfo() {
	}

	public MemberUserInfo(String userId, String userName, String userPhone, String userLocation, String userPassword, Integer askCount, Integer dealCount,
			Integer breakCount, String dataState, Date createTime, String userVldcode, String userType, String userLevel, String userState, Float userAccount,
			String cityCode) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userLocation = userLocation;
		this.userPassword = userPassword;
		this.askCount = askCount;
		this.dealCount = dealCount;
		this.breakCount = breakCount;
		this.dataState = dataState;
		this.createTime = createTime;
		this.userVldcode = userVldcode;
		this.userType = userType;
		this.userLevel = userLevel;
		this.userState = userState;
		this.userAccount = userAccount;
		this.cityCode = cityCode;
	}

	/**
     * userId
     *
     * @return  the userId
     */
    
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * userName
     *
     * @return  the userName
     */
    
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * userPhone
     *
     * @return  the userPhone
     */
    
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone the userPhone to set
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * userLocation
     *
     * @return  the userLocation
     */
    
    public String getUserLocation() {
        return userLocation;
    }

    /**
     * @param userLocation the userLocation to set
     */
    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    /**
     * userPassword
     *
     * @return  the userPassword
     */
    
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * askCount
     *
     * @return  the askCount
     */
    
    public Integer getAskCount() {
        return askCount;
    }

    /**
     * @param askCount the askCount to set
     */
    public void setAskCount(Integer askCount) {
        this.askCount = askCount;
    }

    /**
     * dealCount
     *
     * @return  the dealCount
     */
    
    public Integer getDealCount() {
        return dealCount;
    }

    /**
     * @param dealCount the dealCount to set
     */
    public void setDealCount(Integer dealCount) {
        this.dealCount = dealCount;
    }

    /**
     * breakCount
     *
     * @return  the breakCount
     */
    
    public Integer getBreakCount() {
        return breakCount;
    }

    /**
     * @param breakCount the breakCount to set
     */
    public void setBreakCount(Integer breakCount) {
        this.breakCount = breakCount;
    }

    /**
     * dataState
     *
     * @return  the dataState
     */
    
    public String getDataState() {
        return dataState;
    }

    /**
     * @param dataState the dataState to set
     */
    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    /**
     * createTime
     *
     * @return  the createTime
     */
    
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * userVldcode
     *
     * @return  the userVldcode
     */
    
    public String getUserVldcode() {
        return userVldcode;
    }

    /**
     * @param userVldcode the userVldcode to set
     */
    public void setUserVldcode(String userVldcode) {
        this.userVldcode = userVldcode;
    }

    /**
     * userType
     *
     * @return  the userType
     */
    
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * userLevel
     *
     * @return  the userLevel
     */
    
    public String getUserLevel() {
        return userLevel;
    }

    /**
     * @param userLevel the userLevel to set
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * userState
     *
     * @return  the userState
     */
    
    public String getUserState() {
        return userState;
    }

    /**
     * @param userState the userState to set
     */
    public void setUserState(String userState) {
        this.userState = userState;
    }

    /**
     * userAccount
     *
     * @return  the userAccount
     */
    
    public Float getUserAccount() {
        return userAccount;
    }

    /**
     * @param userAccount the userAccount to set
     */
    public void setUserAccount(Float userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * cityCode
     *
     * @return  the cityCode
     */
    
    public String getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode the cityCode to set
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    
    
   
	
    
}

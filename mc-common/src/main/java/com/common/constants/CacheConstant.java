 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.constants;

/**
 * 
 * <b>Description：</b>redis 缓存key常量类 <br/>
 * <b>ClassName：</b> CacheConstant <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月28日 下午5:43:41 <br/>
 * <b>@version: </b>  <br/>
 */
public class CacheConstant {
	/**
	 * 用户认证-会员用户,后面跟userId
	 */
	public static final String P_AUTH_MEMBER_USER = "P_MEMBER_USER_";
	/**
	 * 用户认证-微信端会员用户,后面跟openId
	 */
	public static final String P_AUTH_WEIXIN_USER = "WEIXIN_";
	/**
     * 用户认证-商家用户,后面跟userId
     */
    public static final String P_AUTH_MERCHANT_USER = "P_MERCHANT_USER_";
    
    /**
     * 用户认证-运营用户,后面跟userId
     */
    public static final String P_AUTH_OPERATORUSER = "P_OPERATOR_USER_";
	
	//*************************token开始****************************/
    /**
     * 会员用户token键
     *//*
    public static final String MEMBERUSER_ACCESSTOKEN_KEY="MEMBER_ACCESSTOKEN_";
    
    public static final String MEMBERUSER_REFRESHTOKEN_KEY="MEMBER_REFRESHTOKEN_";
    
    *//**
     * 商户token键
     *//*
    public static final String MERCHANTUSER_ACCESSTOKEN_KEY="MERCHANTUSER_ACCESSTOKEN_";
    
    public static final String MERCHANTUSER_REFRESHTOKEN_KEY="MERCHANTUSER_REFRESHTOKEN_";
    
    *//**
     * 运营用户token键
     *//*
    public static final String OPERATORUSER_ACCESSTOKEN_KEY="OPERATORUSER_ACCESSTOKEN_";
    
    public static final String OPERATORUSER_REFRESHTOKEN_KEY="OPERATORUSER_REFRESHTOKEN_";*/
   
    //*************************token结束****************************/
    
    /**
     * 会员用户验证码key
     */
    public static final String MEMBER_SMSCODE_KEY="MEMBER_SMSCODE_";
    
    public static final String MEMBER_SMSCODE_TIMES_KEY="MEMBER_SMSCODE_TIMES_";

}

 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.enums.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.enums.LoginTypeEnum;

/**
 * 
 * <b>Description：</b> 认证状态<br/>
 * <b>ClassName：</b> AuthCodeEnum <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月29日 上午10:34:58 <br/>
 * <b>@version: </b>  <br/>
 */
public enum AuthCodeEnum {

    //会员用户端请求认证=============================================
	AUTH_REQUEST_PARAM_ERROR("P-1001","用户认证请求参数不正确"),
    //用户登录验证
    USER_DISA_ERROR( "P-1002","用户被禁用"),
    USER_LOGIN_SUCCESS("P-1003","用户登录成功"),
    USER_LOGIN_FAIL("P-1004","用户登录失败"),
    USER_VALID_ERROR("P-1005","登录验证失败，验证码错误"),
    USER_LOGIN_OUT_FAIL("P-1006","用户登出失败"),
    USER_NAME_PASSWORD_EMPTY("p-1007","用户名或者密码为空"),
    USER_NAME_PASSWORD_ERROR("P-1008","用户名或者密码错误"),
    AUTH_LOGIN_TOO_MANY("P-1009","用户登录已超过限定次数，请稍候再试！"),
    
    //token验证
    USER_GET_TOKEN_ERROR("P-1010","生成token失败"),
    USER_TOKEN_ERROR("P-1011","token失效"),
    USER_ACCESS_TOKEN_ERROR("P-1012","accessToken失效,请重新获取"),
    USER_REFRESH_TOKEN_ERROR("P-1013","refresh token失效，请重新登录"),
    
    //注册验证
    PHONE_REGEX_ERROR("P-1014","手机号码格式不正确"),
    PHONE_VALID_OLD_ERROR("P-1015","手机号是否注册验证失败"),
    USER_PHONE_USED("P-1016","手机号码已被注册"),
    USER_GET_SMSCODE("P-1017","请求验证码失败，请稍后再试"),
    PHONE_SMSCODE_EMPTY("P-1018","验证码不存在，请重新请求验证码"),
    PHONE_SMSCODE_ERROR("P-1019","验证码输入错误"),
    USER_FREQUENT_SMSCODE("P-1020","频繁请求验证码，请稍后再试"),
    USER_REGISTER_ERROR("P-1021","手机号注册失败"),
    USER_REGISTER_SUCCESS("P-1022","手机号注册成功"),
    
    //IP频繁请求
    USER_FREQUENT_IP("P-1023","IP频繁请求，请稍后再试"),
    
    //微信绑定
    USER_WEIXIN_BIND_IP("P-1024","微信OPENID未绑定成功"),

    //商户端请求认证=============================================
    //token
    MECHARNT_USER_GET_TOKEN_ERROR("P-2010","商户端生成token失败"),
    MECHARNT_USER_TOKEN_ERROR("P-2011","商户端token失效"),
    MECHARNT_USER_ACCESS_TOKEN_ERROR("P-2012","商户端accessToken失效,请重新获取"),
    MECHARNT_USER_DECRYPT_ACCESS_TOKEN_ERROR("P-2013","解析token失效"),
    //用户验证
    MECHARNT_USER_DISA_ERROR( "P-2002","商户端用户被禁用"),
    MECHARNT_USER_LOGIN_SUCCESS("P-2003","商户端用户登录成功"),
    MECHARNT_USER_LOGIN_FAIL("P-2004","商户端用户登录失败"),
    MECHARNT_USER_ERROR("P-2014","商户端用户不存在"),
    //手机号
    MECHARNT_USER_PHONE_USED_MANY("P-2016","手机号码被注册多次"),
    //验证码
    MECHARNT_USER_GET_SMSCODE("P-2017","商户端请求验证码失败，请稍后再试"),
    MECHARNT_PHONE_SMSCODE_EMPTY("P-2018","商户端验证码不存在，请重新请求验证码"),
    MECHARNT_PHONE_SMSCODE_ERROR("P-2019","商户端验证码输入错误"),
    MECHARNT_USER_FREQUENT_SMSCODE("P-2020","商户端频繁请求验证码，请稍后再试"),

    
    ;
    /** 描述 */
    private String desc;
    /** 枚举值 */
    private String value;

    private AuthCodeEnum(String value, String desc) {
        this.desc = desc;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据枚举值获取枚举属性.
     * 
     * @param value
     *            枚举值.
     * @return enum 枚举属性.
     */
    public static LoginTypeEnum getEnum(int value) {
        LoginTypeEnum resultEnum = null;
        LoginTypeEnum[] enumAry = LoginTypeEnum.values();
        for (int num = 0; num < enumAry.length; num++) {
            if (enumAry[num].getValue() == value) {
                resultEnum = enumAry[num];
                break;
            }
        }
        return resultEnum;
    }

    /**
     * 将枚举类转换为map.
     * 
     * @return Map<key, Map<attr, value>>
     */
    public static Map<String, Map<String, Object>> toMap() {
        LoginTypeEnum[] ary = LoginTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(ary[num].getValue()));
            map.put("value", ary[num].getValue());
            map.put("desc", ary[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    /**
     * 将枚举类转换为list.
     * 
     * @return List<Map<String, Object>> list.
     */
    public static List<Map<String, Object>> toList() {
        LoginTypeEnum[] ary = LoginTypeEnum.values();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < ary.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("value", ary[i].getValue());
            map.put("desc", ary[i].getDesc());
            list.add(map);
        }
        return list;
    }

	public Throwable getMsg() {
		// TODO Auto-generated method stub
		return null;
	}
}

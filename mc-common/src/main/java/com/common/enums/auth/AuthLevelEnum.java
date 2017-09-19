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
 * <b>Description：</b> 认证级别  token level<br/>
 * <b>ClassName：</b> AuthLevelEnum <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月29日 上午10:34:58 <br/>
 * <b>@version: </b>  <br/>
 */
public enum AuthLevelEnum {
	
	/**
	 * IP次数时间限制
	 */
	IP_LAST_HIGH("IP次数访问时间限制", 60),
	/**
	 * 短信验证码
	 */
	SMSCODE_HIGH("获取验证码次数时间限制",60),
	/**
	 * SMSCODE_VALID_TIME:短信有效时间
	 */
	SMSCODE_VALID_TIME("短信有效时间",1*24*3600),
	
    /**
     * token失效级别高
     */
    TOKEN_HIGH("Token失效时间30分钟", 30*60),
    /**
     * token失效级别中
     */
    TOKEN_MIDDLE("token失效时间1天", 1*24*3600),
    /**
     * token失效级别低
     */
    TOKEN_LOW("token失效30天", 30*24*3600),
    
    TOKEN_MAX_LOW("token失效10年", 10*365*24*3600)
    ;
    /** 描述 */
    private String desc;
    /** 枚举值 */
    private int value;

    private AuthLevelEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
}

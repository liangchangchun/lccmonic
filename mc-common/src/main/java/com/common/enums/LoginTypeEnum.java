 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  用户类型
 * <b>Description：</b> 用户类型 <br/>
 * <b>ClassName：</b> UserTypeEnum <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月12日 下午3:17:22 <br/>
 * <b>@version: </b>  <br/>
 */
public enum LoginTypeEnum {

	/**
	 * 商户4s店
	 */
	MERCHANT("在线商户", 1),
	/**
	 * 个人/会员
	 */
	MEMBER("个人/会员", 2),
	/**
	 * 运营用户
	 */
	OPERATOR("运营用户", 3),
    
    /**
     * 系统用户，主要是运维人员
     */
	SYSTEM("系统用户", 4);
	

	/** 描述 */
	private String desc;
	/** 枚举值 */
	private int value;

	private LoginTypeEnum(String desc, int value) {
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

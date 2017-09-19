 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @描述: 证件类型.
 * @创建: 2014-6-18,下午2:05:23
 * @版本: V1.0
 *
 */
public enum PlatformTypeEnum {
	
	WX_PLATFORM("微信平台","1");
	
	private String desc;
	private String value;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	PlatformTypeEnum(String desc , String value){
		this.desc = desc;
		this.value = value;
	}
	
	public static PlatformTypeEnum getEnum(String value) {
		PlatformTypeEnum resultEnum = null;
		PlatformTypeEnum[] enumAry = PlatformTypeEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue() == value) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		PlatformTypeEnum[] ary = PlatformTypeEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", String.valueOf(ary[num].getValue()));
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}
}

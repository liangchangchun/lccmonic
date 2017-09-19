 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.monitor.cache.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <b>Description：</b> 服务提供者 <br/>
 * <b>ClassName：</b> MonitorStatusEnum <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月12日 下午3:17:22 <br/>
 * <b>@version: </b>  <br/>
 */
public enum CacheCategoryEnum {
    
    PUBLIC("公共服务", "public"),
	
    USER("用户服务", "user"),
	
    TRADE("交易服务", "trade"),
	
    MERCHANT("商户服务", "merchant"),
	
    WARE("商品服务", "ware"),
	
    ACTIVITY("活动服务", "activity"),
    
    REPORT("报表服务", "report"),
    
    OPENLAB("开放服务", "openlab"),
    
    HELP("帮购服务", "help"),
    
    CLOUD("数据云服务", "cloud"),
    
    OMS("运营管理平台", "oms"),
    
    MPS("商户管理平台", "mps"),
;
	

	/** 描述 */
	private String desc;
	/** 枚举值 */
	private String value;

	private CacheCategoryEnum(String desc, String value) {
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
	public static CacheCategoryEnum getEnum(String value) {
		CacheCategoryEnum resultEnum = null;
		CacheCategoryEnum[] enumAry = CacheCategoryEnum.values();
		for (int num = 0; num < enumAry.length; num++) {
			if (enumAry[num].getValue().equals(value)) {
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
		CacheCategoryEnum[] ary = CacheCategoryEnum.values();
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
		CacheCategoryEnum[] ary = CacheCategoryEnum.values();
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

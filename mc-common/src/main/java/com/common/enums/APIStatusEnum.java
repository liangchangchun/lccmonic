 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *  <b>Description：</b> API返回状态 <br/>
 * <b>ClassName：</b> APIStatusEnum <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月12日 下午3:24:23 <br/>
 * <b>@version: </b>  <br/>
 */
public enum APIStatusEnum {

	SUCCESS("操作成功", "1"), FAIL("系统出错，请稍后再试！", "0");

	/**
	 * 存贮值
	 */
	private String value;
	/**
	 * 显示值
	 */
	private String label;

	private APIStatusEnum(String label, String value) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static APIStatusEnum getEnum(String value){
		APIStatusEnum resultEnum = null;
		APIStatusEnum[] enumAry = APIStatusEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue() == value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		APIStatusEnum[] ary = APIStatusEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", String.valueOf(ary[num].getValue()));
			map.put("label", ary[num].getLabel());
			enumMap.put(key, map);
		}
		return enumMap;
	}

}

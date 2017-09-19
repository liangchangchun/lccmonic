/**
* 上海轩言网络信息科技有限公司
* Copyright (c) 2016, xuanyan All Rights Reserved.
*/
package com.common.enums;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.common.utils.string.StringUtil;

/**
 * 
 * <b>Description：</b> 好买车应用类型 <br/>
 * <b>ClassName：</b> APIStatusEnum <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月12日 下午3:24:23 <br/>
 * <b>@version: </b> <br/>
 */
public enum AppEnum {

    HMC_PORTAL_PC("好买车官网门户pc端", "101"), 
    HMC_PORTAL_H5("好买车官网门户h5端", "102"), 
    HMC_PORTAL_IOS("好买车官网门户IOS端","103"), 
    HMC_PORTAL_ANDROID("好买车官网门户Android端", "104"), 
    HMC_PORTAL_H5_WEIXIN("好买车官网门户h5微信端", "105"),
    HMC_MPS_PC("好买车商家PC端", "201"), 
    HMC_MPS_H5("好买车商家H5端","202"),
    HMC_MPS_IOS("好买车商家IOS端", "203"),
    HMC_MPS_ANDROID("好买车商家Android端","204"),
    HMC_OMS_PC("好买车运营管理平台PC", "301");

    /**
     * 存贮值
     */
    private String value;

    /**
     * 显示值
     */
    private String label;

    private AppEnum(String label, String value) {
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

    public static AppEnum getEnum(String value) {
        AppEnum resultEnum = null;
        AppEnum[] enumAry = AppEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (enumAry[i].getValue() == value) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        AppEnum[] ary = AppEnum.values();
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

    public static Set<String> getValues() {
        AppEnum[] ary = AppEnum.values();
        Set<String> reSet = new HashSet<String>();
        for (int num = 0; num < ary.length; num++) {
            reSet.add(String.valueOf(ary[num].getValue()));
        }
        return reSet;
    }
    /**
     * 校验来源是否是门户
     * isPortal <br/> 
     * @param source
     * @return  boolean <br/>
     */
    public static boolean isPortal(String source){
        if(StringUtil.isEmpty(source)){
           return false;
        }
        if(AppEnum.HMC_PORTAL_ANDROID.getValue().equals(source)
                ||AppEnum.HMC_PORTAL_IOS.getValue().equals(source)
                ||AppEnum.HMC_PORTAL_H5.getValue().equals(source)
                ||AppEnum.HMC_PORTAL_H5_WEIXIN.getValue().equals(source)
                ||AppEnum.HMC_PORTAL_PC.getValue().equals(source)){
            return true;
        }
        return false;
    }
    
    /**
     * 校验来源是否是运营管理系统
     * isPortal <br/> 
     * @param source
     * @return  boolean <br/>
     */
    public static boolean isOMS(String source){
        if(StringUtil.isEmpty(source)){
           return false;
        }
        if(AppEnum.HMC_OMS_PC.getValue().equals(source)){
            return true;
        }
        return false;
    }
    
    /**
     * 校验来源是否是商户端系统
     * isPortal <br/> 
     * @param source
     * @return  boolean <br/>
     */
    public static boolean isMPS(String source){
        if(StringUtil.isEmpty(source)){
           return false;
        }
        if(AppEnum.HMC_MPS_PC.getValue().equals(source)||
           AppEnum.HMC_MPS_H5.getValue().equals(source)||  
           AppEnum.HMC_MPS_IOS.getValue().equals(source)||
           AppEnum.HMC_MPS_ANDROID.getValue().equals(source)
                ){
            return true;
        }
        return false;
    }

}

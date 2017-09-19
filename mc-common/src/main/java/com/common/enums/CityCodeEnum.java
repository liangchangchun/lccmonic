package com.common.enums;

/**
 * 
 * <b>Description：</b> 城市行政编号 <br/>
 * <b>ClassName：</b> CityCodeEnum <br/>
 * <b>@author：</b> cheney CHU <br/>
 * <b>@date：</b> 2016年8月11日 下午2:18:43 <br/>
 * <b>@version: </b>  <br/>
 */
public enum CityCodeEnum {
    /**
     * 上海
     */
    SHANGHAI("310000"),

    /**
     *  杭州
     */
    HANGHZHOU("330100");

    CityCodeEnum(String value){
        this.value=value;
    }

    private String value;

    public String getValue(){
        return this.value;
    }

}

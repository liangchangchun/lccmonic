
/**
* 上海轩言网络信息科技有限公司
* Copyright (c) 2016, xuanyan All Rights Reserved.
*/
package com.common.utils.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * <b>Description：</b> JSON工具类 <br/>
 * <b>ClassName：</b> JsonUtil <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月26日 下午2:29:25 <br/>
 * <b>@version: </b> <br/>
 */
public class JsonUtil {

    
//    private static String dateFormat;  
//    static {  
//        dateFormat = "yyyy-MM-dd HH:mm:ss";  
//    }  
    /**
     * json check
     */
    private final static String LEFT_DKH = "{";

    private final static String RIGHT_DKH = "}";
    
    /**
     * check is json string
     * 
     * @param jsonStr
     * @return true is json string
     */
    public static boolean isJsonStr(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return false;
        }
        if (jsonStr.startsWith(LEFT_DKH) && jsonStr.endsWith(RIGHT_DKH)) {
            return true;
        }
        return false;
    }
    /**
     * 
     * 判断json是否为空 <br/>
     * toJsonString <br/>
     * 
     * @param obj
     * @return String <br/>
     */
    public static boolean isEmpty(Object json) {
        if (json == null) {
            return true;
        }
        JSONObject jo = JSON.parseObject(toJson(json));
        if (jo.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 将对象转成json字符串 <br/>
     * toJsonString <br/>
     * 
     * @param obj
     * @return String <br/>
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 
     * 将json字符串转为对象 <br/>
     * parseObject <br/>
     * 
     * @param json
     * @param clazz
     * @return T <br/>
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        return (T) JSON.parseObject(json, clazz);
    }

    /**
     * 将json字符串转为List parseList <br/>
     * 
     * @param json
     * @param clazz
     * @return List<T> <br/>
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    
    
    /**
     * 
     * 将json转为map <br/>
     * jsonToMap <br/>
     * 
     * @param json
     * @return Map<String,Object> <br/>
     */
    public static Map<String, Object> jsonToMap(String json) {
        return JSON.parseObject(json);
    }

    public static void main(String[] args) {
        
        Map<String,Object> hashMap= new HashMap<String,Object>();
        System.out.println(JsonUtil.isEmpty(hashMap));
        
       String s= JsonUtil.toJson(new Object());
       System.out.println(s);
        
    }
}

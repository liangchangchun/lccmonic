
 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2017, xuanyan All Rights Reserved.
 */
package com.common.utils;



 /**
  * 依据 schema 返回对应地址
 * <b>Description：</b> URL 转换工具类 <br/>
 * <b>ClassName：</b> UrlConvertUtils <br/>
 * <b>@author：</b> richardxie <br/>
 * <b>@date：</b> 2017年5月17日 下午6:04:57 <br/>
 * <b>@version: </b>  <br/>
 */
public class UrlConvertUtils {

    private final static String HTTP_IMAGE_HOST="http://static.haomaiche.com/";
    
    private final static String HTTPS_IMAGE_HOST="https://statics.haomaiche.com/";
    
    
    public static String convert(String schema,String url){
        if(url==null||"".equals(url.trim())){
            return "";
        }
        if("https".equals(schema.trim().toLowerCase())){
            return url.replace(HTTP_IMAGE_HOST, HTTPS_IMAGE_HOST);
        }
        
        return url;
    }
    
}

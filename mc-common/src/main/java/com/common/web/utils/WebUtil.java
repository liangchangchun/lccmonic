 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.web.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.common.enums.ErrorCodeEnum;
import com.common.model.stdo.APIRequest;
import com.common.utils.json.JsonUtil;
import com.common.web.exceptions.WebException;
import com.xuanyan.hmc.midware.assist.encode.EncodeUtils;

/**
 * 
 * <b>Description：</b> Web操作工具类. <br/>
 * <b>ClassName：</b> WebUtil <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月28日 下午3:05:31 <br/>
 * <b>@version: </b>  <br/>
 */
public final class WebUtil {
    private static Logger logger = LoggerFactory.getLogger(WebUtil.class);
	/**
	 * 私有构造方法.
	 */
	private WebUtil() {}
	
	/**
	 * 获取客户端的真实IP地址.<br/>
	 * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，那么取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * @param request .
	 * @return IP.
	 */
	public static String getIpAddr(HttpServletRequest request) {
	       String ip = request.getHeader("X-Forwarded-For");
//	       logger.info("X-Forwarded-For -ip=" + ip);  
	       if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("X-Real-IP");
               logger.info("X-Real-IP -ip=" + ip);  
	       }
	       if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
	          ip = request.getHeader("Proxy-Client-IP");
              logger.info(" Proxy-Client-IP ip=" + ip);  
	       }
	       if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("WL-Proxy-Client-IP");
	           logger.info("WL-Proxy-Client-IP -ip=" + ip);  
	       }
	       if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
              ip = request.getHeader("HTTP_CLIENT_IP");
               logger.info("HTTP_CLIENT_IP - ip=" + ip); 
           }
	       if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
               ip = request.getHeader("HTTP_X_FORWARDED_FOR");
               logger.info("HTTP_X_FORWARDED_FOR ip=" + ip);
           }
	       //多级代理的情况
	       if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip) && ip.contains(",")){
	           logger.info("多级代理的情况 ip=" + ip); 
	           String[] ips = ip.split(",");  
	            for (int index = 0; index < ips.length; index++) {  
	                String strIp = (String) ips[index];  
	                if (!("unknown".equalsIgnoreCase(strIp))) {  
	                    ip = strIp;  
	                    break;  
	                }  
	            }  
	            logger.info("muilty proxy ip=" + ip); 
	       }
	     
	       if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
               logger.info("getRemoteAddr ip=" + ip); 
           }
	     /*  Map<String, String> ips= new HashMap<String, String>();
	       ips.put("X-Forwarded-For", request.getHeader("X-Forwarded-For"));
	       ips.put("X-Real-IP", request.getHeader("X-Real-IP"));
	       ips.put("Proxy-Client-IP", request.getHeader("Proxy-Client-IP"));
	       ips.put("WL-Proxy-Client-IP", request.getHeader("WL-Proxy-Client-IP"));
	       ips.put("HTTP_CLIENT_IP", request.getHeader("HTTP_CLIENT_IP"));
	       ips.put("HTTP_X_FORWARDED_FOR", request.getHeader("HTTP_X_FORWARDED_FOR"));
	       ips.put("getRemoteAddr", request.getHeader("getRemoteAddr"));
	       logger.info("get real ip for request is:" + ip +",ip from list=" +ips); */
	       return ip;
	}
	
	/**
	 * 根据request和sessionKey获取session（如果调用处能提供request时则可调用此方法，性能高）.
	 * @param request .
	 * @param sessionKey .
	 * @return sessionValue .
	 */
	public static Object getSession(HttpServletRequest request, String sessionKey){
		return request.getSession().getAttribute(sessionKey);
	}
	
	/**
	 * 保存Session值（如果调用处能提供request时则可调用此方法，性能高）.
	 * @param request .
	 * @param sessionKey .
	 * @param sessionValue .
	 */
	public static void putSession(HttpServletRequest request, String sessionKey, Object sessionValue){
		request.getSession().setAttribute(sessionKey, sessionValue);
	}
	
	
	/**
	 * 根据session名称删除session值.
	 * @param request .
	 * @param sessionKey .
	 */
	public static void removeSession(HttpServletRequest request, String sessionKey){
		request.getSession().removeAttribute(sessionKey);
	}

	/**
	 * 添加Cookie值（切记，为防止XSS劫持Cookie攻击，在向客户端返回Cookie值时记得设置HttpOnly）.
	 * @param response .
	 * @param name cookie的名称 .
	 * @param value cookie的值 .
	 * @param maxAge cookie存放的时间(以秒为单位,假如存放三天,即3*24*60*60; 如果值为0, cookie将随浏览器关闭而清除).
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	/**
	 * 根据某一Cookie名获取Cookie的值.
	 * @param request .
	 * @param name Cookie的名称 .
	 * @return Cookie值.
	 */
	public static String getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = WebUtil.readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = cookieMap.get(name);
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 * 从request中读取所有Cookie值,放入Map中.
	 * @param request .
	 * @return cookieMap.
	 */
	private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int num = 0; num < cookies.length; num++) {
				cookieMap.put(cookies[num].getName(), cookies[num]);
			}
		}
		return cookieMap;
	}
	
	public static APIRequest getAPIRequestByParamenters(HttpServletRequest request){
	    APIRequest apirequest =null;
        try {
            String submitMehtod = request.getMethod();
            // GET
            if (submitMehtod.equals("GET")) {
                String queryString = request.getQueryString();
                
                if(JsonUtil.isJsonStr(queryString)){//json格式
                    queryString = queryString ==null?"{}": EncodeUtils.urlDecode(queryString);
                    apirequest= JSON.parseObject(queryString, APIRequest.class);
                }else{//普通参数
                    Map properties = getRequestParamters(request);
                    if(properties==null){
                        properties = new HashMap<>();
                    }
                    Map<String, Object> paramMap= new HashMap<String, Object>();
                    paramMap.put("source", properties.get("source"));
                    paramMap.put("time", properties.get("time"));
                    paramMap.put("wx_openID", properties.get("wx_openID"));
                    paramMap.put("opTicket", properties.get("opTicket"));
                    properties.remove("source");
                    properties.remove("time");
                    properties.remove("wx_openID");
                    properties.remove("opTicket");
                    paramMap.put("data", properties);
                    apirequest= JSON.parseObject(JSON.toJSONString(paramMap), APIRequest.class);
                }
                return apirequest;
            } else {// POST|PUT|DELETE
                String postJsonStr = getRequestPostStr(request);
               if( JsonUtil.isJsonStr(postJsonStr)){//JSON格式
                   apirequest = JSON.parseObject(postJsonStr, APIRequest.class);
                  // throw new WebException(ErrorCodeEnum.JSON_CHECK_ERROR.getCode(), ErrorCodeEnum.JSON_CHECK_ERROR.getMsg());
               }else {
                   Map properties = getRequestParamters(request);
                   if(properties==null){
                       properties = new HashMap<>();
                   }
                   Map<String, Object> paramMap= new HashMap<String, Object>();
                   paramMap.put("source", properties.get("source"));
                   paramMap.put("time", properties.get("time"));
                   paramMap.put("wx_openID", properties.get("wx_openID"));
                   paramMap.put("opTicket", properties.get("opTicket"));
                   properties.remove("source");
                   properties.remove("time");
                   properties.remove("wx_openID");
                   properties.remove("opTicket");
                   paramMap.put("data", properties);
                   apirequest= JSON.parseObject(JSON.toJSONString(paramMap), APIRequest.class);
               }
                return apirequest;
            }
        } catch (Exception e) {
          logger.error("basecontroller-ajax输入异常！", e);
            
            if(e instanceof WebException){
                WebException we= (WebException) e;
                throw new WebException(we.getErrorCode(), we.getErrorMsg());
            }else {
                
                throw new WebException(ErrorCodeEnum.WEB_REQUEST_ERROR.getCode(), ErrorCodeEnum.WEB_REQUEST_ERROR.getMsg());
            }
        }
	}
	
	 /**
     * 获得所有请求的参数
     * 
     * @param request
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map getRequestParamters(HttpServletRequest request) {
        // 获取所有的请求参数
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        // 读取map中的值
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = " ";
                returnMap = JSON.parseObject(name);
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (String value2 : values) {
                    value = value2 + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            // 将读取到的值存入map中
            returnMap.put(name, value);
        }
        return returnMap;
    }
    
    /**
     * 描述:获取 post 请求的 byte[] 数组
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i, contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request) throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        if(buffer==null){
            return null;
        }
        
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }
}

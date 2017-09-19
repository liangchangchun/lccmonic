 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.web.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.common.auth.manager.TokenLogic;
import com.common.auth.manager.TokenLogicImpl;
import com.common.constants.AuthConstants;
import com.common.enums.APIStatusEnum;
import com.common.enums.AppEnum;
import com.common.enums.ErrorCodeEnum;
import com.common.model.stdo.APIRequest;
import com.common.model.stdo.APIResult;
import com.common.model.vo.auth.TokenModel;
import com.common.model.vo.auth.login.UserInfo;
import com.common.utils.json.JsonUtil;
import com.common.utils.spring.SpringContextHolder;
import com.common.utils.string.StringUtil;
import com.common.utils.validate.ValidateUtils;
import com.common.web.exceptions.WebException;
import com.common.web.exceptions.WebExceptionHandler;
import com.common.web.utils.CookieUtils;
import com.common.web.utils.WebUtil;
import com.xuanyan.hmc.midware.assist.encode.EncodeUtils;

/**
 * 基础controller <b>@author：</b> mobing <br/>
 * <b>@date：</b> 2016年5月6日 下午3:31:16 <br/>
 * <b>@version: </b> <br/>
 */
public class BaseController {

   private static Logger logger = LoggerFactory.getLogger(BaseController.class);
   
   private static Logger accessLogger = LoggerFactory.getLogger("LOGGER.HMC_ACCESS");

   private TokenLogic tokenLogic = TokenLogicImpl.getInstance();
   /**
    * cookie 缓存时间 60天
    */
   private final static int COOKIE_MAXAGE = 60*60*24*60 ;
   /**
    * 含绑定的wx_OpenID快捷登录
    */
   protected boolean PASS_FAST_LOGIN = true; 
   
    /**
     * jsonp回调函数名称
     */
    protected String jsonpCallback = null;

//    /**
//     * 请求传来的参数名称，json格式
//     */
//    protected String params = null;
//
    /**
     * request的map参数
     */
    @SuppressWarnings("rawtypes")
    protected Map paramMap = null;
//
//    /**
//     * 前台传来的json数据
//     */
//    protected JSONObject jsonData = null;
//
    /**
     * 返回给ajax的json数据
     */
    protected JSONObject retJson = null;

    /**
     * HttpServletRequest
     */
    private HttpServletRequest request;

    /**
     * HttpServletResponse
     */
    private HttpServletResponse response;

    /**
     * 设置request和response
     * 
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * 获取request对象
     * 
     * @return
     */
    public HttpServletRequest getRequest() {
        return this.request;
    }

    /**
     * 获取response对象
     * 
     * @return
     */
    public HttpServletResponse getResponse() {
        return this.response;
    }

    /**
     * 获取session对象
     * 
     * @return
     */
    public HttpSession getSession() {
        return this.request.getSession(true);
    }

    protected APIRequest apiRequest;
    

	/**
     * controller初始化参数 create by:jackyshang
     */
    public void initParams() {
        //获取apirequest
        apiRequest = getAPIRequestByParamenters();
        accessLogger.info("begin access url:" + getRequest().getRequestURL() + 
                          ",来源："+ apiRequest.getSource()+
                          ",访问用户："+ (getCurrentUser()==null? "游客" :getCurrentUser().getLoginUserName()) +
                          ",访问IP:"+ getIpAddr());         
       
        // 初始化返回json对象
        this.retJson = new JSONObject();
        //设置登录用户信息
        apiRequest.setUserInfo(getCurrentUser());
        apiRequest.setClientIP(getIpAddr());
        apiRequest.setSchema(getRequest().getScheme());
        //获取跨域的jsonpCallback
        this.paramMap = this.getRequestParamters();
        if (null != this.paramMap.get("params")) {
            this.jsonpCallback = (String) this.paramMap.get("jsonpCallback");
        } 
        
        Cookie[] cookies= getRequest().getCookies();
        if(cookies!=null && cookies.length >0){
            for(Cookie cookie : cookies){
                String abFlag =cookie.getName();// get the cookie name
                if("AB-flag".equalsIgnoreCase(abFlag)){
                    apiRequest.setABFlag(cookie.getValue());
                }
                
               
            }
        }
        initWxCookies();
    }
    /**
     * 记录微信OpenID
     */
    public void initWxCookies(){
    	 Cookie[] cookies= getRequest().getCookies();
    	 if(cookies!=null && cookies.length >0){
    		 for(Cookie cookie : cookies){
    		 String openID = cookie.getValue();
    		 String wxOpenID =cookie.getName();
             if ("wx_openID".equalsIgnoreCase(wxOpenID) && !Strings.isNullOrEmpty(openID)){
             	logger.info("微信ID wx_openID 4:"+ openID);
             	this.apiRequest.setWxOpenID(openID);
             	logger.info("**********base one apiRequest  login :"+this.apiRequest);
             	//wxFastLogin(openID);
             	writeCookie("wx_openID",openID);
             }
            
    	 }
    	 }
    }
    
	
    
    /**
     * 登录成功后写入cookies
     * @param tokenModel
     */
    public void writeCookies(TokenModel tokenModel){
    	if (tokenModel!=null && tokenModel.getAuth()!=null){
    		try {
    			 Calendar cal=Calendar.getInstance();
		  		 cal.add(Calendar.SECOND, Long.valueOf(tokenModel.getAuth().getAccessToken_expires_in()).intValue());
		  		 
		  		Map<String,Object> userLoginInfo = Maps.newHashMap();
		    	userLoginInfo.put("accessToken",tokenModel.getAuth().getAccessToken());
		    	userLoginInfo.put("accessTokenExpiresIn",cal.getTimeInMillis());
		    	userLoginInfo.put("refreshToken",tokenModel.getAuth().getRefreshToken());
		    	
		    	 cal.add(Calendar.SECOND, Long.valueOf(tokenModel.getAuth().getRefreshToken_expires_in()).intValue());
		    	 
		    	userLoginInfo.put("refreshTokenExpiresIn",cal.getTimeInMillis());
		    	userLoginInfo.put("phoneNumber",tokenModel.getUserInfo().getLoginCode());
		    	userLoginInfo.put("userName",tokenModel.getUserInfo().getLoginUserName());
		    	if (!Strings.isNullOrEmpty(tokenModel.getUserInfo().getWxOpenId())) {
		        	userLoginInfo.put("wxOpenId",tokenModel.getUserInfo().getWxOpenId());
		      }
    	logger.info("========"+ CookieUtils.HMC_USER_LOGIN_INFO + " : " + URLEncoder.encode(JSON.toJSONString(userLoginInfo),"UTF-8"));
			writeCookie(CookieUtils.HMC_USER_LOGIN_INFO , URLEncoder.encode(JSON.toJSONString(userLoginInfo),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("cookie信息写入失败！", e);
		}
    	}
    }
    /**
     * 登录成功后写入cookies
     * @param tokenModel
     */
    public void writeCookies(APIResult apiResult){
    	//如果token不为空
	  	if (apiResult.getData()!=null) {
	  		TokenModel tokenModel =(TokenModel)apiResult.getData();
	  		writeCookies(tokenModel);
	  	}
    }
    /**
     * 登录成功后写入cookie
     * @param tokenModel
     */
    public void writeCookie(String key,String value){
    	CookieUtils.setCookie(getRequest(), getResponse(), key,value,COOKIE_MAXAGE);
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

    
    protected String getIpAddr() {  
//        String ip = request.getHeader("x-forwarded-for");  
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("PRoxy-Client-IP");  
//        }  
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("WL-Proxy-Client-IP");  
//        }  
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getRemoteAddr();  
//        }
//        
//        int index = ip.indexOf(',');
//        if (index != -1) {
//            return ip.substring(0, index);
//        } else {
//            return ip;
//        }
        
        return WebUtil.getIpAddr(request);
    } 
    
    /**
     * ajax输出
     * 
     * @param content
     * @param type
     * @return
     */
    public APIRequest getAPIRequestByParamenters() {
        
        return WebUtil.getAPIRequestByParamenters(this.getRequest());
        
//        APIRequest apirequest =null;
//        try {
//            String submitMehtod = request.getMethod();
//            // GET
//            if (submitMehtod.equals("GET")) {
//                String queryString = request.getQueryString();
//                
//                if(JsonUtil.isJsonStr(queryString)){//json格式
//                    queryString = queryString ==null?"{}": EncodeUtils.urlDecode(queryString);
//                    apirequest= JSON.parseObject(queryString, APIRequest.class);
//                }else{//普通参数
//                    Map properties = getRequestParamters();
//                    if(properties==null){
//                        properties = new HashMap<>();
//                    }
//                    Map<String, Object> paramMap= new HashMap<String, Object>();
//                    paramMap.put("source", properties.get("source"));
//                    paramMap.put("time", properties.get("time"));
//                    paramMap.put("wx_openID", properties.get("wx_openID"));
//                    properties.remove("source");
//                    properties.remove("time");
//                    properties.remove("wx_openID");
//                    paramMap.put("data", properties);
//                    apirequest= JSON.parseObject(JSON.toJSONString(paramMap), APIRequest.class);
//                }
//                return apirequest;
//            } else {// POST|PUT|DELETE
//                String postJsonStr = getRequestPostStr(request);
//               if( JsonUtil.isJsonStr(postJsonStr)){//JSON格式
//                   apirequest = JSON.parseObject(postJsonStr, APIRequest.class);
//                  // throw new WebException(ErrorCodeEnum.JSON_CHECK_ERROR.getCode(), ErrorCodeEnum.JSON_CHECK_ERROR.getMsg());
//               }else {
//                   Map properties = getRequestParamters();
//                   if(properties==null){
//                       properties = new HashMap<>();
//                   }
//                   Map<String, Object> paramMap= new HashMap<String, Object>();
//                   paramMap.put("source", properties.get("source"));
//                   paramMap.put("time", properties.get("time"));
//                   paramMap.put("wx_openID", properties.get("wx_openID"));
//                   properties.remove("source");
//                   properties.remove("time");
//                   properties.remove("wx_openID");
//                   paramMap.put("data", properties);
//                   apirequest= JSON.parseObject(JSON.toJSONString(paramMap), APIRequest.class);
//               }
//                return apirequest;
//            }
//        } catch (Exception e) {
//          logger.error("basecontroller-ajax输入异常！", e);
//            
//            if(e instanceof WebException){
//                WebException we= (WebException) e;
//                throw new WebException(we.getErrorCode(), we.getErrorMsg());
//            }else {
//                
//                throw new WebException(ErrorCodeEnum.WEB_REQUEST_ERROR.getCode(), ErrorCodeEnum.WEB_REQUEST_ERROR.getMsg());
//            }
//        }
//        return null;
    }

    /**
     * 获得所有请求的参数
     * 
     * @param request
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getRequestParamters() {
        // 获取所有的请求参数
        Map properties = this.getRequest().getParameterMap();
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
     * ajax输出
     * 
     * @param content
     * @param type
     * @return
     */
    public String ajax(String content, String type) {
        try {
            HttpServletResponse response = this.getResponse();
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            PrintWriter out = response.getWriter();
            out.println(content);
            // out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("basecontroller-ajax输入异常！", e);
        }
        return null;
    }

    /**
     * ajax,mime类型，text/html
     * 
     * @param xml
     * @return
     */
    @SuppressWarnings("unused")
    private String ajaxText(String text) {
        return this.ajax(text, "text/plain");
    }

    /**
     * ajax,mime类型，text/html
     * 
     * @param xml
     * @return
     */
    @SuppressWarnings("unused")
    private String ajaxHtml(String html) {
        return this.ajax(html, "text/html");
    }

    /**
     * ajax,mime类型，text/xml
     * 
     * @param xml
     * @return
     */
    @SuppressWarnings("unused")
    private String ajaxXml(String xml) {
        return this.ajax(xml, "text/xml");
    }

    /**
     * ajax,mime类型，text/javascript
     * 
     * @param xml
     * @return
     */
    private String ajaxJavaScript(String xml) {
        return this.ajax(xml, "text/javascript");
    }

    /**
     * 公共的处理和封装返回给前台ajax的数据
     * 
     * @param jo
     */
    public void renderJson4JsonObj(JSONObject jo) {
        String redata = null;
        redata = (jo == null ? "" : jo.toJSONString());
        // 如果jsonpCallback回调函数不为空，则说明是jsonp方式的请求，则封装格式
        // 否则为普通的ajax提交
        if (StringUtils.isNotEmpty(this.jsonpCallback)) {
            StringBuffer sb = new StringBuffer();
            redata = sb.append(this.jsonpCallback).append("(").append(redata).append(")").toString();
        }
        this.ajaxJavaScript(redata);
    }

    /**
     * 公共的处理和封装返回给前台ajax的数据
     * 
     * @param jo
     */
    public void renderJson4Str(String reval) {
        String redata = null;
        this.retJson = JSONObject.parseObject(reval);
        redata = (this.retJson == null ? new JSONObject().toString() : this.retJson.toString());
        // 如果jsonpCallback回调函数不为空，则说明是jsonp方式的请求，则封装格式
        // 否则为普通的ajax提交
        if (StringUtils.isNotEmpty(this.jsonpCallback)) {
            StringBuffer sb = new StringBuffer();
            redata = sb.append(this.jsonpCallback).append("(").append(redata).append(")").toString();
        }
        this.ajaxJavaScript(redata);
    }


    /**     
     * 获取当前用户 <br/> 
     * getCurrentUser <br/> 
     * @return userInfo
     */
    public UserInfo getCurrentUser(){
		String accessToken = request.getHeader(AuthConstants.REQ_KEY_ACCESS_TOKEN);
        if(ValidateUtils.isNull(accessToken)||"null".equals(accessToken)){
            return null;
        }
		UserInfo userInfo=tokenLogic.getUserInfoByToken(accessToken);
		return userInfo;
	}
    /**     
     * 清除当前用户token <br/> 
     * validateCurrentUser <br/> 
     * @return  APIResult <br/>   
     */
    public APIResult invalidCurrentUser(){
    	String accessToken = request.getHeader(AuthConstants.REQ_KEY_ACCESS_TOKEN);
		if(ValidateUtils.isNull(accessToken)||"null".equals(accessToken)){
			return null;
		}
		//UserInfo userInfo=tokenLogic.getUserInfoByToken(accessToken);
    	tokenLogic.deleteUserToken(accessToken, null);
		return new APIResult();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public APIResult handleException(Exception ex, HttpServletRequest request) {

        logger.error("web api happened error!",ex);
         return WebExceptionHandler.handleException(ex, request);
    }
    
    
}

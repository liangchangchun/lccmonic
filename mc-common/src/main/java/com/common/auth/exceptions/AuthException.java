 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.auth.exceptions;

import java.util.HashMap;
import java.util.Map;

import com.common.utils.json.JsonUtil;

/**
 * 
 * <b>Description：</b> 自定义业务异常 <br/>
 * <b>ClassName：</b> DALException <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月12日 上午9:51:19 <br/>
 * <b>@version: </b>  <br/>
 */
public class AuthException extends RuntimeException {

	private static final long serialVersionUID = -5875371379845226068L;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected String code;

	public AuthException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}
	

	public AuthException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public String getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public  AuthException newInstance(String msgFormat, Object... args) {
		return new AuthException(this.code, msgFormat, args);
	}

	public AuthException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthException(Throwable cause) {
		super(cause);
	}

	public AuthException(String message) {
		super(message);
	}


    @Override
    public String getMessage() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", code);
        map.put("msg", msg);
        return JsonUtil.toJson(map);
    }
	
	
	
}

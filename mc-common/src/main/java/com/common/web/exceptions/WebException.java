 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.web.exceptions;

import java.text.MessageFormat;
import java.util.UUID;
/**
 * 
 * <b>Description：</b> Web应用异常基类，所有Web应用异常都必须继承于此异常 <br/>
 * <b>ClassName：</b> WebException <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月28日 下午3:01:04 <br/>
 * <b>@version: </b>  <br/>
 */
public class WebException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5636524086210777927L;

	/**
	 * 异常ID，用于表示某一异常实例，每一个异常实例都有一个唯一的异常ID
	 */
	protected String id;

	/**
	 * 异常信息，包含必要的上下文业务信息，用于打印日志
	 */
	protected String errorMsg;

	/**
	 * 具体异常码，由各具体异常实例化时自己定义
	 */
	protected String errorCode;

	/**
	 * 异常类名
	 */
	protected String realClassName;

	public WebException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.id = UUID.randomUUID().toString().toUpperCase()
				.replaceAll("-", "");
		this.errorMsg = errorMsg;
	}

	public String getId() {
		return id;
	}



	public void setMessage(String message, Object... args) {
		this.errorMsg = MessageFormat.format(message, args);
	}

    /**
     * errorMsg
     *
     * @return  the errorMsg
     */
    
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * errorCode
     *
     * @return  the errorCode
     */
    
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


}

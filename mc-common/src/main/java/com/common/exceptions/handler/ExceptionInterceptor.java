 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */ 
package com.common.exceptions.handler;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

import com.common.exceptions.BizException;
import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;


/**
 * className：ExceptionInterceptorLog <br>
 * Function：异常的处理 拦截 <br>
 * date: 2014-12-16-上午10:12:37 <br>
 * 
 * @author laich
 */

public class ExceptionInterceptor implements ThrowsAdvice {

	private static final Logger log = LoggerFactory.getLogger(ExceptionInterceptor.class);

	/**
	 * 对未知异常的处理. <br>
	 * Method method 执行的方法 Object[] args <br>
	 * 方法参数 Object target <br>
	 * 代理的目标对象 Throwable BizException 产生的异常 <br>
	 */
	public void afterThrowing(Method method, Object[] args, Object target, BizException ex) {

		log.info("==>ExceptionInterceptorLog.BizException");
		log.info("==>errCode:" + ex.getCode() + " errMsg:" + ex.getMsg());
		log.info("==>" + ex.fillInStackTrace());
	}

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {

		log.error("==>ExceptionInterceptorLog.Exception");

		log.error("==>Error class: " + target.getClass().getName());
		log.error("==>Error method: " + method.getName());

		for (int i = 0; i < args.length; i++) {
			log.error("==>args[" + i + "]: " + args[i]);
		}

		log.error("==>Exception class: " + ex.getClass().getName());
		log.error("==>" + ex.fillInStackTrace());
		ex.printStackTrace();
	}

}

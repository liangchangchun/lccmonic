/**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.utils.redis;

import org.apache.log4j.Logger;

import com.common.model.vo.auth.UserAuth;

/**
 * 将用户会话信息保存在Redis中的工具类
 * 
 * @author zws
 * 
 */
public class RedisSessionUtils {

	private static Logger logger = Logger.getLogger(RedisSessionUtils.class);
	/**
	 * 默认过期时间
	 */
	private static final Integer DEFAULT_SESSION_TIMEOUT = 60 * 30;

	/**
	 * 保存session
	 * 
	 * @param sessionId
	 * @param sessionObj
	 * @param seconds
	 *            超时秒数，如果为null则默认为30分钟
	 * @return
	 */
	public static boolean saveSession(String sessionId, UserAuth sessionObj, Integer seconds) {
		boolean result = false;
		try {
			if (seconds != null) {
			    
			    RedisUtils.save(sessionId, sessionObj);
			} else {
			    RedisUtils.save(sessionId, sessionObj,DEFAULT_SESSION_TIMEOUT);
			}
		} catch (Exception e) {
			logger.error("缓存删除失败：" + e);
		}
		return result;
	}
	
	   public static boolean saveSession(String sessionId, UserAuth sessionObj) {
	        return saveSession(sessionId,sessionObj,null);
	    }

	/**
	 * 删除session
	 * 
	 * @param sessionId
	 * @return
	 */
	public static boolean deleteSession(String sessionId) {
		boolean result = false;
		try {
		    result = RedisUtils.del(sessionId);
			
		} catch (Exception e) {
			logger.error("缓存删除失败：" + e);
		}
		return result;
	}

	/**
	 * 取session
	 * 
	 * @param sessionId
	 * @return
	 */
	public static UserAuth getSession(String sessionId) {
	    UserAuth sessionObj = null;
		try {
		    sessionObj =  RedisUtils.get(sessionId);
		} catch (Exception e) {
			logger.error("缓存读取失败：" + e);
		}
		return sessionObj;
	}

	/**
	 * 更新session超时时间
	 * 
	 * @param sessionId
	 * @param seconds
	 * @return
	 */
	public static boolean updateSessionTime(String sessionId, Integer seconds) {
		boolean result = false;
		try {
		    Long re= -1L;
			if (seconds == null) {
			   re=  RedisUtils.expire(sessionId, DEFAULT_SESSION_TIMEOUT);
			    
			} else {
			     re=  RedisUtils.expire(sessionId, seconds);
			}
			if(re==-1L){
			    return false;
			}else {
			    return true;
			}
		} catch (Exception e) {
			logger.error("缓存时间更新失败：" + e);
		}
		return result;
	}

}

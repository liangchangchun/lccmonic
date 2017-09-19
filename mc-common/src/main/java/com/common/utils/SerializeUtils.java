 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;

/**
 * 
 * <b>Description：</b> 序列化、反序列化工具类 <br/>
 * <b>ClassName：</b> SerializeUtils <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月28日 下午3:09:31 <br/>
 * <b>@version: </b>  <br/>
 */
public class SerializeUtils {

	private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static byte[] serialize(Object object) throws Exception {
		if(object == null) return null;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static Object unSerialize(byte[] bytes) throws Exception {
		if(bytes == null) return null;
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
}

 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */

package com.common.utils.rsa;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName:HashUtil
 * Description: 用来生成Id的hash算法
 * Date:     2015年8月25日 上午10:01:35 
 * @author   mobing
 * @version  	 
 */
public class HashUtil {

	/** 
     *  MurMurHash算法，是非加密HASH算法，性能很高， 
     *  比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免） 
     *  等HASH算法要快很多，而且据说这个算法的碰撞率很低. 
     */  
    public static Long hashNum(String key) {  
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());  
        int seed = 0x1234ABCD;  
        ByteOrder byteOrder = buf.order();  
        buf.order(ByteOrder.LITTLE_ENDIAN);  
  
        long m = 0xc6a4a7935bd1e995L;  
        int r = 47;  
  
        long h = seed ^ (buf.remaining() * m);  
  
        long k;  
        while (buf.remaining() >= 8) {  
            k = buf.getLong();  
  
            k *= m;  
            k ^= k >>> r;  
            k *= m;  
  
            h ^= k;  
            h *= m;  
        }  
  
        if (buf.remaining() > 0) {  
            ByteBuffer finish = ByteBuffer.allocate(8).order(  
                    ByteOrder.LITTLE_ENDIAN);  
            // for big-endian version, do this first:   
            // finish.position(8-buf.remaining());   
            finish.put(buf).rewind();  
            h ^= finish.getLong();  
            h *= m;  
        }  
  
        h ^= h >>> r;  
        h *= m;  
        h ^= h >>> r;  
  
        buf.order(byteOrder);  
        return h;  
    }  
    
    /**
     * 用于生成dochash,或者key
     * 
     * @param input
     * @return docid or "" if input is blank or exception occured
     */
    public static String hashStr(String input) {
        if (StringUtils.isBlank(input)) {
            return StringUtils.EMPTY;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] b = input.getBytes("utf-8");
            md.update(b);
            byte[] rst = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rst.length; i++) {
                String s = Integer.toHexString(rst[i] & 0Xff);
                if (s.length() == 1)
                    sb.append("0");
                sb.append(s);
            }

            return sb.toString();
        } catch (Exception ignore) {
        }
        return StringUtils.EMPTY;
    }
    
    public static void main (String[] args){
    	Long id =hashNum("http://www.b5m.com?id=1235");
    	
    	String idStr =hashStr("http://www.b5m.com?id=1235");
    	System.out.println(id +"------"+ idStr);
    }
	
}


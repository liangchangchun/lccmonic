/**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016-2026 HMC,Inc.All Rights Reserved.
 */
package com.common.utils.redis;

import com.xuanyan.hmc.midware.redis.core.RedisFactory;
import com.xuanyan.hmc.midware.redis.core.handler.RedisHandler;
import com.xuanyan.hmc.midware.redis.core.operation.HashOperations;
import com.xuanyan.hmc.midware.redis.core.operation.KeyOperations;
import com.xuanyan.hmc.midware.redis.core.operation.ListOperations;
import com.xuanyan.hmc.midware.redis.core.operation.SetOperations;
import com.xuanyan.hmc.midware.redis.core.operation.StringsOperations;
import com.xuanyan.hmc.midware.redis.core.operation.ZSetOperations;

import redis.clients.jedis.Jedis;

/**
 * 
 * <b>Description：</b> Redis缓存工具类. <br/>
 * <b>ClassName：</b> RedisUtils <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月28日 下午3:17:03 <br/>
 * <b>@version: </b>  <br/>
 */
public class RedisUtils {

    /** 默认缓存时间 */
    public static final int DEFAULT_CACHE_SECONDS = 60 * 60 * 24;// 单位秒 设置成24小时
   // private static RedisHandler handler = RedisFactory.getInstance().getHandler();
    
    public  static Jedis getClient(){
       return RedisFactory.getInstance().getClient();
    }
    /**
     * 检查key是否存在
     * exists <br/> 
     * @param key
     * @return  boolean <br/>
     */
    public  static <K> boolean exists(K key) {
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.opsForKey().exists(key);
    }
    
    /**
     * 清空redis <br/> 
     * del <br/> 
     * @param key
     * @return  boolean <br/>
     */
    public static void clear() {
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        handler.clear();
    }
    
    /**
     * 获取redis中的数量 <br/> 
     * del <br/> 
     * @param key
     * @return  boolean <br/>
     */
    public static long size() {
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.getSize();
    }
    /**
     * 根据缓存键清除Redis缓存中的值. <br/> 
     * del <br/> 
     * @param key
     * @return  boolean <br/>
     */
    public static boolean del(Object... keys) {
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.delete(keys);
    }
    
    /**
     * 设置某个key的过期时间 <br/> 
     * expire <br/> 
     * @param key
     * @param seconds
     * @return  long <br/>
     */
    public static <K> long expire(K key,int seconds) {
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.expire(key, seconds);
    }
    
    /**
     * 设置某个key的过期时间 <br/> 
     * expire <br/> 
     * @param key
     * @param seconds
     * @return  long <br/>
     */
    public static <K> long ttl(K key) {
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return  handler.ttl(key);
    }
    /**
     *保存一个对象到redis中并指定默认时间 <br/> 
     * save <br/> 
     * @param key
     * @param value
     * @param expireTimeSeconds
     * @return  boolean <br/>
     */
    public static <K,V> boolean save(K key, V value){
        RedisHandler handler = RedisFactory.getInstance().getHandler();
       return handler.save(key,value,DEFAULT_CACHE_SECONDS);
    }
    
    /**
     *保存一个对象到redis中并指定过期时间 <br/> 
     * save <br/> 
     * @param key
     * @param value
     * @param expireTimeSeconds
     * @return  boolean <br/>
     */
    public static <K,V> boolean save(K key, V value, int expireTimeSeconds) {
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.save(key,value,expireTimeSeconds);
    }
    
    /**
     * 获取对象
     * get <br/> 
     * @param key
     * @return  V <br/>
     */
    public static <K,V> V get(K key) {
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.get(key);
    }
    
    /**
     * 操作String
     * opsForString <br/> 
     * @return  ListOperations <br/>
     */
    public static StringsOperations opsForString(){
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.opsForString();
    }
    /**
     * 操作list
     * opsForList <br/> 
     * @return  ListOperations <br/>
     */
    public static ListOperations opsForList(){
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.opsForList();
    }
    /**
     * 
     * 操作hash <br/> 
     * opsForHash <br/> 
     * @return  HashOperations <br/>
     */
    public static HashOperations opsForHash(){
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.opsForHash();
    }
    /**
     * 
     * 操作set<br/> 
     * opsForSet <br/> 
     * @return  SetOperations <br/>
     */
    public static SetOperations opsForSet(){
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.opsForSet();
    }
    /**
     * 
     * 操作zset <br/> 
     * opsForZSet <br/> 
     * @return  ZSetOperations <br/>
     */
    public static ZSetOperations opsForZSet(){
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.opsForZSet();
    }
    
    /**
     * 
     * key 操作类 <br/> 
     * opsForZSet <br/> 
     * @return  ZSetOperations <br/>
     */
    public static KeyOperations opsForKey(){
        RedisHandler handler = RedisFactory.getInstance().getHandler();
        return handler.opsForKey();
    }
}

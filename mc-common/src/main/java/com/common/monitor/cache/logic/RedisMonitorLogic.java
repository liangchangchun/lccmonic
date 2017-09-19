
 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.monitor.cache.logic;

import java.util.List;

import com.common.monitor.cache.model.RedisMonitor;

/**
 * <b>Description：</b> 服务提供者监控逻辑 <br/>
 * <b>ClassName：</b> IMonitorLogic <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年8月25日 上午10:41:22 <br/>
 * <b>@version: </b>  <br/>
 */
public interface RedisMonitorLogic {

    /**
     * 清空
     * getList <br/> 
     * @return  List<RedisMonitor.java> <br/>
     */
    public void clean();
    
    /**
     * 获取列表
     * getList <br/> 
     * @return  List<RedisMonitor.java> <br/>
     */
    public List<RedisMonitor> getList();
    
    /**
     * 获取提供者
     * save <br/> 
     * @param RedisMonitor.java  void <br/>
     */
    public  RedisMonitor get(String cacheKey);
    /**
     * 新增服务提供者
     * save <br/> 
     * @param RedisMonitor.java  void <br/>
     */
    public  void save(RedisMonitor cache);
    
    /**
     * 新增服务提供者
     * save <br/> 
     * @param RedisMonitor.java  void <br/>
     */
    public  void saveOrUpdate(RedisMonitor cache);
    
    /**
     * 删除服务提供者
     * delete <br/> 
     * @param RedisMonitor.java  void <br/>
     */
    public  void delete(String cacheKey);
    /**
     * 更新服务提供者
     * update <br/> 
     * @param RedisMonitor.java  void <br/>
     */
    public  void update(RedisMonitor cache);
    
    
}

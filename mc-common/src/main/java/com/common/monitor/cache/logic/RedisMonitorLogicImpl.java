
/**
* 上海轩言网络信息科技有限公司
* Copyright (c) 2016, xuanyan All Rights Reserved.
*/
package com.common.monitor.cache.logic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.common.enums.ZKNameSpaceEnum;
import com.common.monitor.cache.model.RedisMonitor;
import com.common.utils.redis.RedisUtils;
import com.common.zookeeper.ZookeeperFacade;
import com.xuanyan.hmc.midware.assist.utils.date.DateUtil;
import com.xuanyan.hmc.midware.assist.utils.string.StringUtil;
import com.xuanyan.hmc.midware.zookeeper.curator.ZKRegistryCenter;

/**
 * <b>Description：</b> 监控逻辑 <br/>
 * <b>ClassName：</b> MonitorLogic <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年8月25日 上午10:39:05 <br/>
 * <b>@version: </b> <br/>
 */
@Component("redisMonitorLogic")
public class RedisMonitorLogicImpl implements RedisMonitorLogic {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(RedisMonitorLogicImpl.class);

    
    private ZKRegistryCenter getZKClient(){
        return ZookeeperFacade.getZKClient(ZKNameSpaceEnum.MONITOR_CACHE.getValue());
    }
    @Override
    public List<RedisMonitor> getList() {
//        ZKRegistryCenter zk = ZookeeperFacade.getInstance().getZKClient(ZookeeperFacade.NAMESPACE_MONITOR_REDIS_CACHE);
        
        ZKRegistryCenter zk = getZKClient();
        List<String> cacheList = null;

        if (!zk.isExisted(RedisMonitorZKNode.PATH_ROOT)) {
            zk.persist(RedisMonitorZKNode.PATH_ROOT);
        }

        cacheList = zk.getChildrenKeys(RedisMonitorZKNode.PATH_ROOT);
        List<RedisMonitor> list = new ArrayList<RedisMonitor>();
        if (cacheList != null && !cacheList.isEmpty()) {
            RedisMonitorZKNode node = new RedisMonitorZKNode();
            RedisMonitor cache =null;
            String expireStr=null;
            String status="有效";
            Long updateTime= DateUtil.getCurrentDateMilliSecond();
            Long curTime= DateUtil.getCurrentDateMilliSecond();
            for (String cacheKey : cacheList) {
                cache = new RedisMonitor();
                cache.setId(cacheKey);
                expireStr=zk.getData(node.getCacheExpirePath(cacheKey));
                cache.setCacheKey(cacheKey);
                cache.setCategory(zk.getData(node.getCacheCategoryPath(cacheKey)));
                cache.setExpire(expireStr);
                updateTime= Long.valueOf(zk.getData(node.getCacheUpdateTimePath(cacheKey)));
                cache.setUpdateTime(DateUtil.timestampToDate(updateTime));
               
                Long invalid= updateTime+ (Long.valueOf(expireStr) *1000L);
                
                System.out.println("****  curTime" +curTime +",invalid:" +invalid  +",updateTime"+updateTime +",expireStr"+expireStr);
                if(curTime > invalid){
                    status="过期";
//                    zk.remove(node.getCacheRootPath(cacheKey));
                }
                cache.setStatus(status);
                cache.setInvalidTime(DateUtil.timestampToDate(invalid));
                cache.setTtl(String.valueOf(RedisUtils.ttl(cacheKey)));
                
                list.add(cache);
            }
        } 
        return list;
    }
    
    @Override
    public void clean() {
        ZKRegistryCenter zk = getZKClient();
        zk.remove(RedisMonitorZKNode.PATH_ROOT);
    }

    @Override
    public void save(RedisMonitor cache) {
        ZKRegistryCenter zk = getZKClient();
        RedisMonitorZKNode node = new RedisMonitorZKNode();
        zk.persist(node.getCacheKeyPath(cache.getCacheKey()),cache.getCacheKey());
        zk.persist(node.getCacheCategoryPath(cache.getCacheKey()),cache.getCategory());
        zk.persist(node.getCacheExpirePath(cache.getCacheKey()),cache.getExpire());
        zk.persist(node.getCacheUpdateTimePath(cache.getCacheKey()),String.valueOf(DateUtil.getCurrentDateMilliSecond()));
    }

    @Override
    public void saveOrUpdate(RedisMonitor cache) {
        ZKRegistryCenter zk = getZKClient();
        RedisMonitorZKNode node = new RedisMonitorZKNode();
        
        if(zk.isExisted(node.getCacheRootPath(cache.getCacheKey()))){
            update(cache);
        }else {
            save(cache);
        }
    }
    
    @Override
    public void delete(String cacheKey) {
        ZKRegistryCenter zk = getZKClient();
        RedisMonitorZKNode node = new RedisMonitorZKNode();
        if (zk.isExisted(node.getCacheRootPath(cacheKey))) {
            zk.remove(node.getCacheRootPath(cacheKey));
        }
        //redis删除
        RedisUtils.del(cacheKey);
    }

    @Override
    public void update(RedisMonitor cache) {
        ZKRegistryCenter zk = getZKClient();
        RedisMonitorZKNode node = new RedisMonitorZKNode();
        if (StringUtil.isNotEmpty(cache.getCacheKey())) {
            zk.update(node.getCacheKeyPath(cache.getCacheKey()),cache.getCacheKey());
        }
        if (StringUtil.isNotEmpty(cache.getCategory())) {
            zk.update(node.getCacheCategoryPath(cache.getCacheKey()),cache.getCategory());
        }
        if (StringUtil.isNotEmpty(cache.getExpire())) {
            zk.update(node.getCacheExpirePath(cache.getCacheKey()),cache.getExpire());
        }
        zk.update(node.getCacheUpdateTimePath(cache.getCacheKey()),String.valueOf(DateUtil.getCurrentDateMilliSecond()));
    }

    @Override
    public RedisMonitor get(String cacheKey) {
        ZKRegistryCenter zk =getZKClient();
        RedisMonitor cache = new RedisMonitor();
        RedisMonitorZKNode node = new RedisMonitorZKNode();
        cache.setCacheKey(cacheKey);
        cache.setCategory(zk.getData(node.getCacheCategoryPath(cacheKey)));
        cache.setExpire(zk.getData(node.getCacheExpirePath(cacheKey)));
        cache.setUpdateTime(zk.getData(node.getCacheUpdateTimePath(cacheKey)));
        return cache;
    }

   

}

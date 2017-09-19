/**
* 上海轩言网络信息科技有限公司
* Copyright (c) 2016, xuanyan All Rights Reserved.
*/

package com.common.zookeeper;

import java.util.Set;
import java.util.Map.Entry;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.stereotype.Component;

import com.common.enums.ZKNameSpaceEnum;
import com.xuanyan.hmc.midware.zookeeper.curator.ZKRegistryCenter;

/**
 * 好买车服务监控 <b>Date:</b> 2016年6月1日 下午3:56:45<br/>
 * 
 * @author mobing
 * @version
 */
@Component
public class ZookeeperFacade {

    private static ConcurrentHashMap<String, ZKRegistryCenter> zkClientMap = new ConcurrentHashMap<String, ZKRegistryCenter>();

    public void init() {
        Set<String> nsSet = ZKNameSpaceEnum.getValues();
        for (String ns : nsSet) {
            ZKRegistryCenter zkClient = zkClientMap.get(ns);
            if (zkClient == null) {
                start(ns);
                zkClient = zkClientMap.get(ns);
            }
        }
    }

    public static ZKRegistryCenter getZKClient(String nameSpace) {
        ZKRegistryCenter zkClient = zkClientMap.get(nameSpace);
        if (zkClient == null) {
            start(nameSpace);
            zkClient = zkClientMap.get(nameSpace);
        }
        return zkClient;
    }

    /**
     * zk 启动
     * 
     * @param
     * @return void
     */
    public static void start(String nameSpace) {
        // 获取zk注册中心
        ZKRegistryCenter zk = new ZKRegistryCenter(nameSpace);
        // 加入缓存
        zkClientMap.putIfAbsent(nameSpace, zk);
    }

    /**
     * zk关闭
     * 
     * @param
     * @return void
     */
    public void close() {
        for (Entry<String, ZKRegistryCenter> map : zkClientMap.entrySet()) {
            ZKRegistryCenter zk = map.getValue();
            zk.close();
        }
    }
}

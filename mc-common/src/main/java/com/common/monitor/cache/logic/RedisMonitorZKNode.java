
 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.monitor.cache.logic;


 /**
 * <b>Description：</b> monitor监控 <br/>
 * <b>ClassName：</b> Node <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年8月25日 上午10:49:58 <br/>
 * <b>@version: </b>  <br/>
 */
public class RedisMonitorZKNode {
    /**
     * 根节点.
     */
    public static final String PATH_ROOT = "/hmc-redis-monitor";
    //缓存目录
    public static final String PATH_CACHE_ROOT = PATH_ROOT + "/%s";

    public static final String NODE_CACHE_KEY = "cacheKey";
    //缓存key
    public static final String PATH_CACHE_KEY = PATH_CACHE_ROOT + "/cacheKey";
    
    public static final String NODE_CACHE_CATEGORY = "category";
    //缓存渠道
    public static final String PATH_CACHE_CATEGORY = PATH_CACHE_ROOT + "/category";

    public static final String NODE_CACHE_EXPIRE = "expire";
    //缓存过期
    public static final String PATH_CACHE_EXPIRE = PATH_CACHE_ROOT + "/expire";
    
    public static final String NODE_CACHE_UPDATE_TIME = "updateTime";
    //缓存过期
    public static final String PATH_CACHE_UPDATE_TIME = PATH_CACHE_ROOT + "/updateTime";
    

    public  String getCacheRootPath(final String cacheKey) {
        return String.format(PATH_CACHE_ROOT, cacheKey);
    }
    
    public  String getCacheKeyPath(final String cacheKey) {
        return String.format(PATH_CACHE_KEY, cacheKey);
    }

    public   String getCacheCategoryPath(final String cacheKey) {
        return String.format(PATH_CACHE_CATEGORY, cacheKey);
    }
    
    public String getCacheExpirePath(final String cacheKey) {
        return String.format(PATH_CACHE_EXPIRE, cacheKey);
    }

    public String getCacheUpdateTimePath(final String cacheKey) {
        return String.format(PATH_CACHE_UPDATE_TIME, cacheKey);
    }
    
    public boolean isCategoryNode(final String path) {
        return path.startsWith(PATH_ROOT) && path.endsWith(RedisMonitorZKNode.NODE_CACHE_CATEGORY);
    }

    /**
     * 根据全路径截取某个提供者的
     * @param
     * @return String
     */
    public String genCachePathByFull(String path) {
        String cacheRoot = path.substring(0, path.lastIndexOf("/"));
        return cacheRoot;
    }

    /**
     *根据全路径生成服务名称节点的全路径
     * @param
     * @return String
     */
    public String genCacheKeyPathByFull(String path) {
        return genCachePathByFull(path) + "/" + RedisMonitorZKNode.NODE_CACHE_KEY;
    }


}

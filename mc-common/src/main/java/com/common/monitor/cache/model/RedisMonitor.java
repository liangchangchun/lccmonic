
 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2017, xuanyan All Rights Reserved.
 */
package com.common.monitor.cache.model;

/**
 * <b>Description：</b> TODO <br/>
 * <b>ClassName：</b> RedisMonitor <br/>
 * <b>@author：</b> mobing <br/>
 * <b>@date：</b> 2017年6月16日 下午3:18:07 <br/>
 * <b>@version: </b>  <br/>
 */
public class RedisMonitor {

    /**
     * id
     */
    private String id;
    /**
     * 缓存key
     */
    private String cacheKey;
    /**
     * 所属渠道
     */
    private String category;
    /**
     * 有效期
     */
    private String expire;
    /**
     * 状态
     */
    private String status;
    
    /**
     * 失效时间
     */
    private String invalidTime;
    
    /**
     * 剩余时间
     */
    private String ttl;
    /**
     * 最新更新时间
     */
    private String updateTime;
    
    

    /**
     * ttl
     *
     * @return  the ttl
     */
    
    public String getTtl() {
        return ttl;
    }

    /**
     * @param ttl the ttl to set
     */
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    /**
     * invalidTime
     *
     * @return  the invalidTime
     */
    
    public String getInvalidTime() {
        return invalidTime;
    }

    /**
     * @param invalidTime the invalidTime to set
     */
    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    /**
     * status
     *
     * @return  the status
     */
    
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * cacheKey
     *
     * @return  the cacheKey
     */
    
    public String getCacheKey() {
        return cacheKey;
    }

    /**
     * @param cacheKey the cacheKey to set
     */
    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

   

    /**
     * category
     *
     * @return  the category
     */
    
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * id
     *
     * @return  the id
     */
    
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * expire
     *
     * @return  the expire
     */
    
    public String getExpire() {
        return expire;
    }

    /**
     * @param expire the expire to set
     */
    public void setExpire(String expire) {
        this.expire = expire;
    }

    /**
     * updateTime
     *
     * @return  the updateTime
     */
    
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    
}

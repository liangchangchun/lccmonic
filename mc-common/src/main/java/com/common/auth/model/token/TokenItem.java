 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.auth.model.token;

import com.alibaba.fastjson.JSON;
import com.xuanyan.hmc.midware.assist.utils.date.DateUtil;

/**
 * 
 * <b>Description：</b> 用来生成token的数据项 <br/>
 * <b>ClassName：</b> TokenItem <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年8月17日 下午1:44:49 <br/>
 * <b>@version: </b>  <br/>
 */
public class TokenItem {
    /**
     * userId
     */
	private String userId;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 客户端IP
	 */
	private String clientIp;
    /**
     * IOS,安卓 regedit
     */
    private String regedit;
	/**
	 * 创建时间
	 */
	private long creatTime;
	
	public TokenItem(){}
	public TokenItem(String userId,String source,String clientIp){
	    this.userId =userId;
	    this.source=source;
	    this.clientIp=clientIp;
	    this.creatTime = DateUtil.getCurrentDateMilliSecond();
	}
    public TokenItem(String userId,String source,String clientIp,String regedit){
        this.userId =userId;
        this.source=source;
        this.clientIp=clientIp;
        this.regedit=regedit;
        this.creatTime = DateUtil.getCurrentDateMilliSecond();
    }
    /**
     * userId
     *
     * @return  the userId
     */
    
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * source
     *
     * @return  the source
     */
    
    public String getSource() {
        return source;
    }
    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }
    /**
     * clientIp
     *
     * @return  the clientIp
     */
    
    public String getClientIp() {
        return clientIp;
    }
    /**
     * @param clientIp the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
    /**
     * creatTime
     *
     * @return  the creatTime
     */
    
    public long getCreatTime() {
        return creatTime;
    }
    /**
     * @param creatTime the creatTime to set
     */
    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getRegedit() {
        return regedit;
    }

    public void setRegedit(String regedit) {
        this.regedit = regedit;
    }
}

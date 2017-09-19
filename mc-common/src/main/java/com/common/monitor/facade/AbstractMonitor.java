
 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.monitor.facade;

import com.common.model.stdo.APIRequest;
import com.common.model.stdo.APIResult;
import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;

/**
 * <b>Description：</b> 抽象的监控类 <br/>
 * <b>ClassName：</b> AbstractMonitor <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年8月26日 下午4:13:41 <br/>
 * <b>@version: </b>  <br/>
 */
public abstract class AbstractMonitor {
    private static final Logger logger = LoggerFactory.getLogger(AbstractMonitor.class);
    public APIResult connect (APIRequest request) {
        logger.info("开始建立连接。。。。");
        
        
        APIResult result =new APIResult();
        
        return result;
    }
}

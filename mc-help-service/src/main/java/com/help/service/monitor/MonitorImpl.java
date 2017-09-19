
 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.help.service.monitor;

import org.springframework.stereotype.Component;

import com.common.model.stdo.APIRequest;
import com.common.model.stdo.APIResult;
import com.common.monitor.facade.AbstractMonitor;
import com.common.monitor.facade.Monitor_user;

/**
 * <b>Description：</b> 监控服务客户端 <br/>
 * <b>ClassName：</b> MonitorClientImpl <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年8月25日 上午11:38:51 <br/>
 * <b>@version: </b>  <br/>
 */
@Component("monitor_user")
public class MonitorImpl extends AbstractMonitor implements Monitor_user {


    @Override
    public APIResult connect(APIRequest request) {
        
        return  super.connect(request);
    }
   
}

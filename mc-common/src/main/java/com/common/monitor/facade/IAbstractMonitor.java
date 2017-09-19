
 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.monitor.facade;

import com.common.model.stdo.APIRequest;
import com.common.model.stdo.APIResult;

/**
 * <b>Description：</b> TODO <br/>
 * <b>ClassName：</b> II <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年8月26日 下午4:22:55 <br/>
 * <b>@version: </b>  <br/>
 */
public interface IAbstractMonitor  {
    /**
     * 建立连接 <br/> 
     * connect <br/> 
     * @param request
     * @return  APIResult <br/>
     */
    public APIResult connect (APIRequest request);
}


 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.web.exceptions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.rpc.RpcException;
import com.common.enums.ErrorCodeEnum;
import com.common.exceptions.BizException;
import com.common.model.stdo.APIResult;
import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;

/**
 * <b>Description：</b> TODO <br/>
 * <b>ClassName：</b> ExceptionHandler <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年8月11日 下午3:15:25 <br/>
 * <b>@version: </b>  <br/>
 */
public class WebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);
    /**
     * 异常处理器
     * handleException <br/> 
     * @param ex
     * @param request
     * @return  APIResult <br/>
     */
    public static  APIResult handleException(Exception ex, HttpServletRequest request) {
        APIResult result=null;
        logger.trace("==========================出现异常========================");
        if(ex instanceof RemotingException){
            logger.error("dubbo服务Remoting异常,具体错误为："+ex.getMessage(),ex);
            result = new APIResult(ErrorCodeEnum.DUBBO_ERROR.getCode(),ErrorCodeEnum.DUBBO_ERROR.getMsg());
        }else if(ex instanceof RpcException){
            logger.error("dubbo服务Rpc异常,具体错误为："+ex.getMessage(),ex);
            result = new APIResult(ErrorCodeEnum.DUBBO_ERROR.getCode(),ErrorCodeEnum.DUBBO_ERROR.getMsg());
        }else if(ex instanceof IOException){
            logger.error("请求资源找不到,具体错误为："+ex.getMessage(),ex);
            result = new APIResult(ErrorCodeEnum.IO_ERROR.getCode(),ErrorCodeEnum.IO_ERROR.getMsg());
        }else if(ex instanceof WebException){
            WebException webException=(WebException) ex;
            logger.error("web请求异常，具体错误为："+ex.getMessage(),webException);
            result = new APIResult(webException.getErrorCode(),webException.getErrorMsg());
        }       
        else if(ex instanceof BizException){
            BizException bizException=(BizException) ex;
            logger.error("业务异常，请稍后再试！具体错误为："+ex.getMessage(),bizException);
            
            result = new APIResult(bizException.getCode(),bizException.getMsg());
        }else{
            logger.error("系统错误，请稍后再试！具体错误为：" +ex.getMessage(),ex);
            result = new APIResult(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg() + ex.getMessage() );
        }
         return result;
    }
}

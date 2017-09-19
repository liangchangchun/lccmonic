
/**
* 上海轩言网络信息科技有限公司
* Copyright (c) 2016, xuanyan All Rights Reserved.
*/
package com.common.base.filter.dubbo;



import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.common.exceptions.BizException;
import com.common.model.stdo.APIResult;
import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;

/**
 * <b>Description：</b> Dubbo exception 过滤器 <br/>
 * <b>ClassName：</b> AuthorityFilter <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月14日 下午5:36:41 <br/>
 * <b>@version: </b> <br/>
 */
public class ExceptionHandlerFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String clientIP = RpcContext.getContext().getRemoteHost();
        APIResult r = new APIResult();
        Result result=null;
        try {
            result = invoker.invoke(invocation);
            //如果返回结果为apiresult的，则直接返回
            if (result.getValue()!=null &&result.hasException()) {
                Throwable exception = result.getException();
                //System.out.println("--------------1111----------------" + exception  +"| result" + result);
                // 如果是checked异常，直接抛出
                if (exception instanceof BizException) {
                    LOGGER.error( invocation.getClass().getCanonicalName()+ "调用异常！IP="+ clientIP+"异常消息");
                    return new RpcResult(new RpcException(exception.getMessage()));
                }else {
                    LOGGER.error(exception);
                    return new RpcResult(new RpcException(exception.getMessage()));
                    
                }
            }
        } catch (BizException e) {
            r.setErrorCode(e.getCode());
            r.setMsg(e.getMsg());
            LOGGER.error("---------------BizException-------------",e);
            return new RpcResult(new BizException(e.getCode(),e.getMsg()));
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("---------------BizException-------------",e);
            return new RpcResult(new BizException(e));
        }
        return result;
    }

}

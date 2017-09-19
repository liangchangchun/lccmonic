package com.common.web.exceptions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.rpc.RpcException;
import com.common.enums.ErrorCodeEnum;
import com.common.exceptions.BizException;
import com.common.model.stdo.APIResult;
import com.common.utils.json.JsonUtil;
import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;


 /**
 * <b>Description：</b> 添加异常resolver <br/>
 * <b>ClassName：</b> WebExceptionResolver <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年8月25日 下午4:45:23 <br/>
 * <b>@version: </b>  <br/>
 */
public class WebExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);

	@Override
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		APIResult result = null;
		logger.trace("==========================出现异常========================");
		if (ex instanceof RemotingException) {
			logger.info("dubbo服务Remoting异常,具体错误为：" + ex.getMessage());
			result = new APIResult(ErrorCodeEnum.DUBBO_ERROR.getCode(), ErrorCodeEnum.DUBBO_ERROR.getMsg());
		} else if (ex instanceof RpcException) {
			logger.info("dubbo服务Rpc异常,具体错误为：" + ex.getMessage());
			result = new APIResult(ErrorCodeEnum.DUBBO_ERROR.getCode(), ErrorCodeEnum.DUBBO_ERROR.getMsg());
		} else if (ex instanceof IOException) {
			logger.info("请求资源找不到,具体错误为：" + ex.getMessage());
			result = new APIResult(ErrorCodeEnum.IO_ERROR.getCode(), ErrorCodeEnum.IO_ERROR.getMsg());
		} else if (ex instanceof WebException) {
			WebException webException = (WebException) ex;
			logger.info("web请求异常，具体错误为：" + webException.getMessage());
			result = new APIResult(webException.getErrorCode(), webException.getErrorMsg());
		} else if (ex instanceof BizException) {
			BizException bizException = (BizException) ex;
			logger.info("业务异常，请稍后再试！具体错误为：" + bizException.getMessage());

			result = new APIResult(bizException.getCode(), bizException.getMsg());
		} else {
			logger.info("系统错误，请稍后再试！具体错误为：" + ex.getMessage());
			result = new APIResult(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getMsg());
		}
		responseOutWithJson(response, result);
		return null;
	}

	/**     
	 * 返回json数据 <br/> 
	 * responseOutWithJson <br/> 
	 * @param response
	 * @param result  void <br/>   
	 */
	protected void responseOutWithJson(HttpServletResponse response, APIResult result) {
		String jsonStr = JsonUtil.toJson(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(jsonStr);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}

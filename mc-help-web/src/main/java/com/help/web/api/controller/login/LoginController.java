/**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016-2026 hmc,Inc.All Rights Reserved.
 */
package com.help.web.api.controller.login;

import com.common.utils.json.JsonUtil;
import com.common.utils.redis.RedisUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.help.facade.service.UserFacade;
import com.common.model.stdo.APIResult;
import com.common.model.vo.auth.login.UserInfo;
import com.common.web.annontation.Auth;
import com.common.web.base.BaseController;
import com.common.web.utils.CookieUtils;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Description：</b> 登录注册控制器类 <br/>
 * <b>ClassName：</b> LoginController <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年8月5日 下午6:31:29 <br/>
 * <b>@version: </b>  <br/>
 */
@Controller
//@RequestMapping(value="/user/member")
@Scope("prototype")
public class LoginController extends BaseController{
 
    @Autowired
    private UserFacade userFacade;
    /**     
     * 验证是否老用户 <br/> 
     * isOldUser <br/> 
     * @return  APIResult <br/>   
     */
    @ResponseBody
	@RequestMapping(value="/user/validate-phone",method =RequestMethod.POST)
	public APIResult isOldUser(){
		APIResult rs=userFacade.login(this.apiRequest);
		return rs;
	}
	

}

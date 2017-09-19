package com.help.facade.service;

import com.common.model.stdo.APIRequest;
import com.common.model.stdo.APIResult;


 /**
 * <b>Description：</b> 公共信息 <br/>
 * <b>ClassName：</b> UserFacade <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年7月27日 下午5:57:54 <br/>
 * <b>@version: </b>  <br/>
 */
public interface UserFacade {

    /**     
     * 用户登录 <br/> 
     * login <br/> 
     * @param request
     * @return  APIResult <br/>   
     */
   APIResult login(APIRequest request);
	
}

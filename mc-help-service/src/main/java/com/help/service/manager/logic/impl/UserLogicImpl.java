package com.help.service.manager.logic.impl;


import org.springframework.stereotype.Component;

import com.help.service.manager.logic.UserLogic;




 /**
 * <b>Description：</b> 用户认证逻辑 <br/>
 * <b>ClassName：</b> UserAuthLogicImpl <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年7月27日 下午5:37:06 <br/>
 * <b>@version: </b>  <br/>
 */
@Component("userLogic")
public class UserLogicImpl implements UserLogic {
	

	 @Override
	 public String isOldUser(String userPhone) {
		 return "1234";
	 }


 }

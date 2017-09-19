 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.web.annontation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.common.enums.auth.AuthLevelEnum;

/**
 * Action层权限配置注解.<br/>
 * 模块标识和功能点标识共同构成唯一权限点.
 * <b>ClassName：</b> Auth <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月29日 下午4:32:41 <br/>
 * <b>@version: </b>  <br/>
 */
@Retention(RetentionPolicy.RUNTIME) //声明注解的保留期限
@Target({ ElementType.METHOD,ElementType.TYPE}) // 声明可以使用该注解的目标类型为在方法中使用
@Documented
@Inherited
public @interface Auth {
	 /**
     * 认证级别
     * @return
     */
    AuthLevelEnum authLevel() default AuthLevelEnum.TOKEN_MIDDLE;
}

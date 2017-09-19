 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.utils.validate;

import java.util.regex.Pattern;

/**
 * 
 * <b>Description：</b> 正则表达式验证 <br/>
 * <b>ClassName：</b> RegularUtil <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月12日 上午10:54:17 <br/>
 * <b>@version: </b>  <br/>
 */
public class RegularUtil {

    /**
     * 
     * 正则表达式 <br/> 
     * validatePattern <br/> 
     * @param regular
     * @param input
     * @return  Boolean <br/>
     */
	public static Boolean validatePattern(String regular, String input) {
		Pattern p = Pattern.compile(regular);
		return p.matcher(input).matches();
	}
}

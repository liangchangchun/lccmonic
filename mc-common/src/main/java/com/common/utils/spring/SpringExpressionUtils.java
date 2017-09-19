
 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2017, xuanyan All Rights Reserved.
 */
package com.common.utils.spring;

import java.lang.reflect.Method;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.xuanyan.hmc.midware.assist.utils.string.StringUtil;

/**
 * <b>Description：</b> TODO <br/>
 * <b>ClassName：</b> SpringExpressionUtils <br/>
 * <b>@author：</b> mobing <br/>
 * <b>@date：</b> 2017年6月15日 下午5:49:15 <br/>
 * <b>@version: </b>  <br/>
 */
public class SpringExpressionUtils {

    /** 
     * 获取缓存的key 
     * key 定义在注解上，支持SPEL表达式 
     * 注： method的参数支持Javabean和Map 
     *      method的基本类型要定义为对象，否则没法读取到名称 
     * 
     * example1: 
     *      Phone phone = new Phone(); 
     *      "#{phone.cpu}"  为对象的取值 
     * example2: 
     *      Map apple = new HashMap(); apple.put("name","good apple"); 
     *      "#{apple[name]}"  为map的取值 
     * example3: 
     *      "#{phone.cpu}_#{apple[name]}" 
     * 
     * @param key 
     * @param method 
     * @param args 
     * @return 
     */  
    public static String parseKey(String key, Method method, Object[] args) {  
        //如果不是SpEL，则直接返回该key
        if(StringUtil.isNotEmpty(key) && key.indexOf("#{")<0){
            return key;
        }
        //获取被拦截方法参数名列表(使用Spring支持类库)  
        LocalVariableTableParameterNameDiscoverer u =  
                new LocalVariableTableParameterNameDiscoverer();  
        String[] paraNameArr = u.getParameterNames(method);  
  
        //使用SPEL进行key的解析  
        ExpressionParser parser = new SpelExpressionParser();  
        //SPEL上下文  
        StandardEvaluationContext context = new StandardEvaluationContext();  
        //把方法参数放入SPEL上下文中  
        for (int i = 0; i < paraNameArr.length; i++) {  
            context.setVariable(paraNameArr[i], args[i]);  
        }  
        ParserContext parserContext = new TemplateParserContext();  
  
        //----------------------------------------------------------  
        // 把 #{ 替换成 #{# ,以适配SpEl模板的格式  
        //----------------------------------------------------------  
        Object returnVal =  
                parser.parseExpression(key.replace("#{","#{#"), parserContext).getValue(context, Object.class);  
        return returnVal == null ? null: returnVal.toString();  
    }  
}

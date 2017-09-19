package com.common.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.common.exceptions.BizException;
import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;

/**
 * spring 实用类<br>
 * <b>ClassName：</b> SpringUtils <br/>
 * <b>Description：</b> TODO <br/>
 * <b>@author：</b> mobing <br/>
 * <b>@date：</b> 2015年11月19日 下午4:30:35 <br/>
 * <b>@version: </b> <br/>
 */
public class SpringUtils {

    private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

    private static volatile ApplicationContext context;

    
    public static void initApplicationContext(String configLocation) {

        try {
            if (context == null) {
                // 如果是maven-jar包打的程序则从项目根下取context，取不到则从类路径下获取
                context = new FileSystemXmlApplicationContext(configLocation);
            }
        } catch (BeansException e) {

            context = new ClassPathXmlApplicationContext(configLocation);
        }
        logger.info("spring 上下文根加载结束" + context);

    }

    /**
     * 从spring中获取bean对象<br>
     * 如果spring初始化不成功或者没有该对象，则返回NULL
     * 
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        try {
            checkSpring();
            return (T) context.getBean(name);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new BizException("error to get spring bean",e);
        }
        //return null;
    }

    private static void checkSpring() {
        if (context != null) {
            return;
        }
        // 最多休息10秒,等待spring初始化完成
        int retry = 50;
        for (int i = 0; i < retry; i++) {
            if (context != null) {
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignore) {
            }
        }
    }
}

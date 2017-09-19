 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.config;

import java.util.Properties;

import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;


/**
 * 过滤器配置文件加载
 * <b>@author：</b> mobing <br/>
 * <b>@date：</b> 2016年2月16日 下午8:25:31 <br/>
 * <b>@version: </b>  <br/>
 */
public enum HConfig {
    CONTEXT("context.properties"),//项目级别
    SYSTEM("public_system.properties"),//全局
    RESOURCE("resource.properties")//资源redis
    ;

    private static Logger logger = LoggerFactory.getLogger(HConfig.class);


    private String fileName;
    
    private static final String configPath="/config/";
    /**
     * 是否已经初始化
     */
    private static boolean inited;
    private Properties props;
    

    private HConfig(String fileName) {
        this.props = new Properties();
        this.fileName = fileName;
        //initConfig();
    }

    private static void initConfig() {
        
        if(inited ){
            return;
        }
        
        for (HConfig config : HConfig.values()) {
            try {
                Properties props = PropertiesUtils.loadProperties(configPath+ config.fileName);
                if (props.size() > 0) {
                    config.props.putAll(props);
                }
            } catch (Exception e) {
                logger.error("loadDefault() from classpath时发生错误", e);
            }
        }
        inited=true;
    }

    private static void checkLoad() {
        // double checking
        synchronized (HConfig.class) {
            initConfig();
        }
}
   

    public Properties getProperties() {
        return this.props;
    }

    public String getProperty(String key) {
        return getProperty(key, null);
    }

    
    
    public String getProperty(String key, String defaultValue) {
        checkLoad();
        String value = props.getProperty(key, defaultValue);
        logger.debug("getProperties: key[" + key + "], value[" + value + "]");
        return value;
    }
    
    public Integer getPropertyInt(String key) {
        checkLoad();
        String value = props.getProperty(key);
        
        if(value!=null){
            return Integer.valueOf(value);
        }
        logger.debug("getProperties: key[" + key + "], value[" + value + "]");
        return null;
    }
    
    public Boolean getPropertyBoolean(String key) {
        checkLoad();
        String value = props.getProperty(key);
        
        if(value!=null){
            return Boolean.valueOf(value);
        }
        logger.debug("getProperties: key[" + key + "], value[" + value + "]");
        return null;
    }
    
    public Long getPropertyLong(String key) {
        checkLoad();
        String value = props.getProperty(key);
        
        if(value!=null){
            return Long.valueOf(value);
        }
        logger.debug("getProperties: key[" + key + "], value[" + value + "]");
        return null;
    }

    public boolean getProperty(String key, boolean defaultValue) {
        boolean value = defaultValue;
        String str = getProperty(key, "" + defaultValue);
        if (str != null && str.length() > 0) {
            try {
                value = Boolean.parseBoolean(str);
            } catch (Exception e) {
                logger.error("配置选项[" + key + "]数据[" + str + "]格式错误，使用默认值[" + defaultValue + "]返回");
            }
        }
        return value;
    }

    public int getProperty(String key, int defaultValue) {
        int value = defaultValue;
        String str = getProperty(key, "" + defaultValue);
        if (str != null && str.length() > 0) {
            try {
                value = Integer.parseInt(str);
            } catch (Exception e) {
                logger.error("配置选项[" + key + "]数据[" + str + "]格式错误，使用默认值[" + defaultValue + "]返回");
            }
        }
        return value;
    }

    public long getProperty(String key, long defaultValue) {
        long value = defaultValue;
        String str = getProperty(key, "" + defaultValue);
        if (str != null && str.length() > 0) {
            try {
                value = Long.parseLong(str);
            } catch (Exception e) {
                logger.error("配置选项[" + key + "]数据[" + str + "]格式错误，使用默认值[" + defaultValue + "]返回");
            }
        }
        return value;
    }
    
    public boolean getBooleanProperty(String key) {
        String pString=  getProperty(key, null);
        if(pString!=null){
           return Boolean.valueOf(pString);
        }
        
        return false;
    }

    

    public static void main(String[] args) {
        String v = HConfig.SYSTEM.getProperty(HConfigKey.KEY_DUBBO_REGISTRY_ADDRESS);
        System.out.println(v);
    }

}

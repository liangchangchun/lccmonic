 /**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.config;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * <b>Description：</b> TODO <br/>
 * <b>ClassName：</b> PropsUtils <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月25日 下午3:49:56 <br/>
 * <b>@version: </b>  <br/>
 */
public class PropertiesUtils {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    /**
     * 先在文件系统里查找配置文件，找不到的话，再在classpath（或jar）包里查找配置文件。
     * 
     * @param path
     * @return
     */
    public static Properties loadProperties(String path) {
        Properties props = loadPropsFromFilepath(path);

        if (props.size() == 0) {
            props = loadPropsFromClasspath(path);
        }


        return props;
    }

    /**
     * 从classpath里或者jar包里加载配置文件，使用getResourceAsStream()加载配置文件；
     * 
     * @param path
     * @return
     */
    public static Properties loadPropsFromClasspath(String path) {
        Properties props = new Properties();

        InputStream inStream = null;
        try {
            inStream = PropertiesUtils.class.getResourceAsStream(path);
            URL url = PropertiesUtils.class.getResource(path);
            if (url != null) {
                props.load(inStream);
                path = url.getFile();
                logger.info("加载配置文件[" + path + "]");
            }
        } catch (Exception ioe) {
            logger.error("找不到配置文件[" + path + "]，请检查配置文件是否存在！", ioe);
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException ie) {
                    logger.error("",ie);
                }
            }
        }

        return props;
    }

    /**
     * 从文件系统里加载配置文件，使用FileInputStream来加载配置文件。
     * 
     * @param path
     * @return
     */
    public static Properties loadPropsFromFilepath(String path) {
        Properties props = new Properties();

        InputStream inStream = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                inStream = new FileInputStream(path);
                props.load(inStream);
                logger.info("加载配置文件[" + path + "]");
            }
        } catch (IOException e) {
            logger.error("找不到配置文件[" + path + "]，请检查配置文件是否存在！", e);
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }

        return props;
    }


}

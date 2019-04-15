package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.alibaba.fastjson.JSON;
import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author trangle
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    public static void main(String[] args){
        System.out.println(toJSONString("tmp.properties"));
    }


    /**
     * properties文件转为json字符串
     *
     * @param resourceName
     *        例如，properties文件位置 ，"config/xxx.properties"
     */
    public static String toJSONString(String resourceName) {
        InputStream inputStream = null;
        Properties props = new Properties();
        try {
            inputStream = Resources.getResource(resourceName).openStream();
            props.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return JSON.toJSONString(props);
    }
}

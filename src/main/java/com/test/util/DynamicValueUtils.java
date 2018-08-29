package com.test.util;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 处理数据屏蔽中的动态值
 * </p>
 *
 * @author yunxiang.zhou01@hand-china.com 2018/08/01 15:26
 */
public class DynamicValueUtils {

    private static final Logger logger = LoggerFactory.getLogger(DynamicValueUtils.class);

    /**
     * 根据编码
     *
     * @param field
     * @return
     */
    public static Object getValue(String field) {
        try {
            return FieldUtils.readDeclaredField(new Object(), field);
        } catch (IllegalAccessException e) {
            if (logger.isErrorEnabled()) {
                logger.error("CustomUserDetails don't have " + field + "field!");
            }
            return field;
        }
    }
}

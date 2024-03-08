package com.test.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

public class AnnotationUtils {

    /**
     * 更新类中注解值
     *
     * @param clazz           类名
     * @param field           字段名
     * @param fieldAnnotation 字段注解类
     * @param annotationField 注解字段值
     * @param changeValue     修改后的值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void updateFieldAnnotationValue(Class clazz, String field, Class<? extends Annotation> fieldAnnotation, String annotationField, String changeValue) throws NoSuchFieldException, IllegalAccessException {
        Field userNameField = clazz.getDeclaredField(field);
        Annotation annotation = userNameField.getAnnotation(fieldAnnotation);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");
        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
        memberValues.put(annotationField, changeValue);
    }

    public static String getAnnotationValue(Class clazz, String field, Class<? extends Annotation> fieldAnnotation) throws NoSuchFieldException {
        Field userNameField = clazz.getDeclaredField(field);
        Annotation annotation = userNameField.getAnnotation(fieldAnnotation);
        return annotation.toString();
    }
}

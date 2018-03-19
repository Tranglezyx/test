package com.test.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

public class ReflectUtils {


    /**
     * 根据值以及定义列中的set方法参数返回一个具体的值
     * @param object 原本值
     * @param c 需要转化的类型
     * @return 转化后的值
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     */
    public Object getResultValueByClassType(String object,Class c,String formatPattern) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ParseException {
        if("".equals(object) || object == null){
            return null;
        }
        String name = c.getSimpleName();
        if("Date".equals(name)){
            Date date = DateUtils.strToDate(object,formatPattern);
            return date;
        }
        Object o = null;
        try{
            Constructor constructor = c.getConstructor(String.class);
            o = constructor.newInstance(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return o;
    }

    /**
     * 根据方法名称获得当前方法的参数
     * @param fieldName 方法名
     * @param object 方法所在的对象
     * @return
     */
    public static Class getFieldNameMethodClass(String fieldName,Object object){
        Method[] methods = object.getClass().getMethods();
        for(Method method : methods){
            if(method.getName().equals(fieldName)){
                Class[] params = method.getParameterTypes();
                if(params.length > 1){
                    throw new RuntimeException(Object.class + "这个dto中的" + fieldName + "方法不允许存在方法重载！");
                }
                if(params.length == 0){
                    throw new RuntimeException(Object.class + "这个dto中的" + fieldName + "方法不允许方法无参数！");
                }
                return params[0];
            }
        }
        return null;
    }
}

package com.test.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author trangle
 */
public class CGLibProxy implements MethodInterceptor {

    public Object newProxy(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLib前置代理 ---");
        Object obj = methodProxy.invokeSuper(o, objects);
        // 调用此方法会造成死循环，原因是
//        Object obj = methodProxy.invoke(o, objects);
        System.out.println("CGLib前置代理 ---");
        return obj;
    }
}

package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object proxy;

    public DynamicProxy(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("代理---");
        // 反射执行
        return method.invoke(proxy, args);
    }
}

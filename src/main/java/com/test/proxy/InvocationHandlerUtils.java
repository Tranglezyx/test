package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerUtils implements InvocationHandler{

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy ------------ " + method.getName());
        System.out.println(proxy);
        return null;
    }
}

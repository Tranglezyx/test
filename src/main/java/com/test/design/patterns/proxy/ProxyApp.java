package com.test.design.patterns.proxy;

import java.lang.reflect.Proxy;

/**
 * @author trangle
 */
public class ProxyApp {

    public static void main(String[] args) {
        Say say = new Say();

        LogInvocationHandler logInvocationHandler = new LogInvocationHandler(say);
        Doing dynamicProxy = (Doing) logInvocationHandler.getProxy();
        dynamicProxy.doing("吃饭");

        Doing dynamicProxy1 = (Doing) Proxy.newProxyInstance(say.getClass().getClassLoader(),say.getClass().getInterfaces(),logInvocationHandler);
        dynamicProxy1.doing("睡觉");

//        Doing staticLogProxy = new StaticLogProxy(say);
//        staticLogProxy.doing("吃饭");

    }
}
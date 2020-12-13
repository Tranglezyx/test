package com.test.design.patterns.proxy;

/**
 * @author trangle
 */
public class ProxyApp {

    public static void main(String[] args) {
        Say say = new Say();

        LogInvocationHandler logInvocationHandler = new LogInvocationHandler(say);
        Doing dynamicProxy = (Doing) logInvocationHandler.getProxy();
        dynamicProxy.doing("吃饭");

        Doing staticLogProxy = new StaticLogProxy(say);
        staticLogProxy.doing("吃饭");

    }
}
package com.test.design.patterns.proxy;

import java.lang.reflect.Proxy;

/**
 * @author trangle
 */
public class ProxyApp {

    public static void main(String[] args) {
        Say say = new Say();
        DynamicProxy dynamicProxy = new DynamicProxy(say);
        Doing doing = (Doing) Proxy.newProxyInstance(Say.class.getClassLoader(), Say.class.getInterfaces(), dynamicProxy);
        doing.doing("吃饭");
    }
}
package com.test.design.patterns.proxy;

/**
 * @author trangle
 */
public class ProxyApp {

    public static void main(String[] args) {
        Say say = new Say();
        LogInvocationHandler logInvocationHandler = new LogInvocationHandler(say);
        Doing doing = (Doing) logInvocationHandler.getProxy();
        doing.doing("吃饭");
    }
}
package com.test.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogInvocationHandler implements InvocationHandler {

    private Object proxy;

    public LogInvocationHandler(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "日志代理---");
        // 反射执行
        return method.invoke(proxy, args);
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), proxy.getClass().getInterfaces(), this);
    }
}

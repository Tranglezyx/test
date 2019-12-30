package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandlerUtils implements InvocationHandler {

    private Object proxied;

    public InvocationHandlerUtils(Object proxied) {
        super();
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("class : " + proxy.getClass() + "   method :" + method.getName() + "   args :" + args);
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i] + "-----proxy";
        }
        Object obj = method.invoke(proxied, args);
        System.out.println("执行完毕");
        return obj;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), proxied.getClass().getInterfaces(), this);
    }
}

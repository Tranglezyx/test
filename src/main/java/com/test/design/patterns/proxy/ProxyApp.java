package com.test.design.patterns.proxy;

//import sun.misc.ProxyGenerator;
//
//import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author trangle
 */
public class ProxyApp {

    public static void main(String[] args) throws IOException {
        Say say = new Say();

        LogInvocationHandler logInvocationHandler = new LogInvocationHandler(say);
//        Doing dynamicProxy = (Doing) logInvocationHandler.getProxy();
        Object dynamicProxy = Proxy.newProxyInstance(say.getClass().getClassLoader(), say.getClass().getInterfaces(), logInvocationHandler);
        ((Doing) dynamicProxy).doing("吃饭");
        ((Doing) dynamicProxy).saying("王八蛋");

        // 获得jdk动态代理生成的新类
//        byte[] bytes = ProxyGenerator.generateProxyClass("$ProxySay", new Class[]{Doing.class});
//        FileOutputStream os = new FileOutputStream("/Users/trangle/Documents/workspace/test/src/main/java/com/test/design/patterns/proxy/$ProxySay.class");
//        os.write(bytes);
//        os.close();

//        Doing dynamicProxy1 = (Doing) Proxy.newProxyInstance(say.getClass().getClassLoader(), say.getClass().getInterfaces(), logInvocationHandler);
//        dynamicProxy1.doing("睡觉");

//        System.out.println(ProxyApp.class.getResource("").getPath());
//        JavaCompiler
//        ToolProvider

//        Doing staticLogProxy = new StaticLogProxy(say);
//        staticLogProxy.doing("吃饭");

    }
}

/*
反编译动态代理自动生成的类文件

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.test.design.patterns.proxy.Doing;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class $ProxySay extends Proxy implements Doing {
    private static Method m1;
    private static Method m3;
    private static Method m2;
    private static Method m0;

    public $ProxySay(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void doing(String var1) throws  {
        try {
            super.h.invoke(this, m3, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() throws  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m3 = Class.forName("com.test.design.patterns.proxy.Doing").getMethod("doing", Class.forName("java.lang.String"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}

 */
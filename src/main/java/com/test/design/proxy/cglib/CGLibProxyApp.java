package com.test.design.proxy.cglib;

/**
 * @author trangle
 */
public class CGLibProxyApp {

    public static void main(String[] args) {
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, CGLibProxyApp.class.getResource("").getPath());
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/trangle/Documents/workspace/test/src/main/java");
        Eating eating = (Eating) new CGLibProxy().newProxy(Eating.class);
        eating.eat("蛋糕");
    }
}

package com.test.design.patterns.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author trangle
 */
public class CGLibProxyApp {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, CGLibProxyApp.class.getResource("").getPath());
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/trangle/Downloads/");
        Eating eating = (Eating) new CGLibProxy().newProxy(Eating.class);
        eating.eat("蛋糕");

    }
}

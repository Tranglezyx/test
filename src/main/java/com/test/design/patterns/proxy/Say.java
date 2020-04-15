package com.test.design.patterns.proxy;

public class Say implements Doing {

    @Override
    public void doing(String s) {
        System.out.println("say：" + s);
    }
}

package com.test.proxy;

public class Say implements Doing {

    @Override
    public void doing(String s) {
        System.out.println("say：" + s);
    }
}

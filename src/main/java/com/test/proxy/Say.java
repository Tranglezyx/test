package com.test.proxy;

public class Say implements Doing{
    @Override
    public void say(String s) {
        System.out.println(s);
    }
}

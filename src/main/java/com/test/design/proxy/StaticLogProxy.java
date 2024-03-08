package com.test.design.proxy;

public class StaticLogProxy implements Doing {

    private Doing doing;

    public StaticLogProxy(Doing doing) {
        this.doing = doing;
    }

    @Override
    public void doing(String s) {
        System.out.println("静态日志代理---");
        doing.doing(s);
    }

    @Override
    public void saying(String s) {

    }
}

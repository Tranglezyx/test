package com.test.design.patterns.decorator;

/**
 * @author trangle
 * <p>
 * 装饰器类
 */
public class CakeDecorator extends Cake {

    private Cake cake;

    public CakeDecorator(Cake cake) {
        this.cake = cake;
    }

    @Override
    protected String getMsg() {
        return this.cake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.cake.getPrice();
    }
}

package com.test.design.patterns.decorator;

/**
 * @author trangle
 */
public class AppleDecorator extends CakeDecorator {

    public AppleDecorator(Cake cake) {
        super(cake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "加一个苹果";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }
}

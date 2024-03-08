package com.test.design.decorator;

/**
 * @author trangle
 */
public class EggDecorator extends CakeDecorator {

    public EggDecorator(Cake cake) {
        super(cake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "加一个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }
}

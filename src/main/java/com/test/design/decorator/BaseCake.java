package com.test.design.decorator;

/**
 * @author trangle
 */
public class BaseCake extends Cake{

    @Override
    protected String getMsg() {
        return "蛋糕";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}

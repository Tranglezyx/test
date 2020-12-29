package com.test.design.patterns.decorator;

/**
 * @author trangle
 * <p>
 * 装饰器模式
 */
public class DecoratorApp {

    public static void main(String[] args) {
        Cake cake;
        cake = new BaseCake();
        cake = new EggDecorator(cake);
        cake = new AppleDecorator(cake);
        cake = new EggDecorator(cake);
        System.out.println(cake.getMsg());
        System.out.println(cake.getPrice());
    }
}

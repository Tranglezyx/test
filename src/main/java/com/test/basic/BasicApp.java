package com.test.basic;

import lombok.Data;

public class BasicApp {
    public static void main(String[] args) {
        String str = "main";
        Integer integer = 1000;
        int i = 1000;
        BasicObject basicObject = new BasicObject();
        basicObject.setI(1000);
        basicObject.setStr("main");
        basicObject.setInteger(1000);

        BasicApp app = new BasicApp();
        app.transmitInteger(integer);
        app.transmitString(str);
        app.transmitInt(i);
        app.transmitObject(basicObject);

        System.out.println(str);
        System.out.println(integer);
        System.out.println(i);
        System.out.println(basicObject);
    }

    /**
     * 测试Java参数传递是引用传递还是值传递
     */
    public void transmitString(String str) {
        str = "transmitString";
    }

    /**
     * 测试Java参数传递是引用传递还是值传递
     */
    public void transmitInteger(Integer integer) {
        integer = 10000;
    }

    public void transmitInt(int i) {
        i = 10000;
    }

    public void transmitObject(BasicObject basicObject) {
        basicObject = new BasicObject();
        basicObject.setI(10000);
        basicObject.setStr("transmitObject");
        basicObject.setInteger(10000);
    }

    @Data
    public static class BasicObject {
        private String str;
        private Integer integer;
        private int i;
    }
}

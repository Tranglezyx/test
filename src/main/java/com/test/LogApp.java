package com.test;

import com.test.util.LogUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(test());

    }

    public static String test() {
        try {
            System.out.println("result");
            return "result";
        } catch (Exception e) {

        } finally {
            System.out.println("finally -- ");
        }
        return "test";
    }
}

package com.test.code;

import java.io.IOException;

/**
 * @author trangle
 */
public class EasyApp {

    public static void main(String[] args) throws IOException {
        Class clazz = EasySolution.class;
        System.out.println(clazz.getClassLoader().getSystemResource("").getFile().toString());
        System.out.println(ClassLoader.getSystemResource("").getFile());

    }
}

package com.code.util;

/**
 * @author trangle
 */
public class SoutUtils {

    public static void printIntArray(int[] objects){
        if(objects != null){
            for (Object object : objects) {
                System.out.printf(object + "\t");
            }
        }
        System.out.println();
    }

    public static void printArray(Object[] objects){
        if(objects != null){
            for (Object object : objects) {
                System.out.printf(object + "\t");
            }
        }
        System.out.println();
    }
}

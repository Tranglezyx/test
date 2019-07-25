package com.test.modifier.function;

/**
 * @author trangle
 */
public class ModifierFunction {

    public static void publicFunction(){
        System.out.println("public ----");
    }

    protected static void protectedFunction(){
        System.out.println("protected ----");
    }

    static void defaultFunction(){
        System.out.println("default ----");
    }

    private static void privateFunction(){
        System.out.println("private ----");
    }
}

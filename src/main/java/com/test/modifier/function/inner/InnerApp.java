package com.test.modifier.function.inner;

import com.test.modifier.function.ModifierFunction;

/**
 * @author trangle
 */
public class InnerApp {

    public static void main(String[] args) {
        ModifierFunction.publicFunction();
        InnerFunction.publicFunction();
        InnerFunction.protectedFunction();
    }
}


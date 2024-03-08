package com.test.feature.function.inner;

import com.test.feature.function.ModifierFunction;

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


package com.test.feature.function.inner;

import com.test.feature.function.ModifierFunction;

/**
 * @author trangle
 */
public class InnerFunction extends ModifierFunction {

    protected static void test(){
        publicFunction();
        protectedFunction();
    }

    protected static void protectedFunction(){
        protectedFunction();
    }
}

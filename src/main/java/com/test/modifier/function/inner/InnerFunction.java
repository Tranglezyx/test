package com.test.modifier.function.inner;

import com.test.modifier.function.ModifierFunction;

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

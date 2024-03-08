package com.test.design.singleton;

/**
 * @author zhouyx
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton() {

    }

    public static LazyInnerClassSingleton newInstance() {
        return Lazy.LAZY;
    }

    private static class Lazy {
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }
}

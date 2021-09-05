package com.test.entity;

/**
 * @author trangle
 */
public class EntityApp {

    public static void main(String[] args) {
        TestEqualsEntity entity1 = new TestEqualsEntity("qqq");
        TestEqualsEntity entity2 = new TestEqualsEntity("qqq");
        System.out.println(entity1==entity2);
    }
}

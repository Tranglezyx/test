package com.test.jar.kafka;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.MockConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;

import java.util.Arrays;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/8/8 15:24
 * @Description:
 */
public class MockConsumerApp {

    public static void main(String[] args) {
        B.fun();
        A.fun();
        B b = new B();
    }

    static class A {
        public static void fun(){
            System.out.println("A----");
        }
    }

    static class B extends A {
        public static void fun(){
            System.out.println("B----");
        }
    }
}

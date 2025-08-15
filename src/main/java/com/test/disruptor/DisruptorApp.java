package com.test.disruptor;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/8/5 10:27
 * @Description:
 */
public class DisruptorApp {

    public static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(1,
            3,
            60,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            ThreadFactoryBuilder.create().setNamePrefix("disruptor-").build(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {

        // 5. 创建事件工厂
        SmsEventFactory factory = new SmsEventFactory();

        // 6. 环形缓冲区大小，必须是2的幂
        int bufferSize = 10240;

        // 7. 创建Disruptor
        Disruptor<SmsEvent> disruptor = new Disruptor<>(
                factory,
                bufferSize,
                EXECUTOR,
                ProducerType.SINGLE, // 单生产者或多生产者
                new BlockingWaitStrategy() // 等待策略
        );

        // 8. 注册事件处理器
        disruptor.handleEventsWith(new SmsEventHandler());

        // 9. 启动Disruptor
        disruptor.start();

        // 10. 获取环形缓冲区
        RingBuffer<SmsEvent> ringBuffer = disruptor.getRingBuffer();

        // 11. 发布事件
        for (long i = 0; i < 10; i++) {
            long sequence = ringBuffer.next();
            try {
                SmsEvent event = ringBuffer.get(sequence);
            } finally {
                ringBuffer.publish(sequence); // 发布事件
            }
        }

        Thread.sleep(1000);
        disruptor.shutdown();
    }

    // 3. 事件处理器
    static class SmsEventHandler implements EventHandler<SmsEvent> {
        @Override
        public void onEvent(SmsEvent event, long sequence, boolean endOfBatch) {
            try {
                Thread.sleep(100);
                System.out.println("处理数据: " + JSONObject.toJSONString(event));
            } catch (InterruptedException e) {

            }
        }
    }

    // 2. 事件工厂
    static class SmsEventFactory implements EventFactory<SmsEvent> {
        @Override
        public SmsEvent newInstance() {
            return new SmsEvent();
        }
    }

    // 1. 定义事件类
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class SmsEvent {

        private String msg;
    }
}



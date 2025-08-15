package com.test.disruptor;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.RandomUtil;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/8/11 14:55
 * @Description:
 */
@Slf4j
public class DisruptorProvider {

    // 6. 环形缓冲区大小，必须是2的幂
    public static final int bufferSize = 1024;


    public static final ThreadPoolExecutor MONITOR_EXECUTOR = new ThreadPoolExecutor(1,
            3,
            60,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            ThreadFactoryBuilder.create().setNamePrefix("test-").build(),
            new ThreadPoolExecutor.AbortPolicy());

    // 5. 创建事件工厂
    public static final SmsEventFactory factory = new SmsEventFactory();

    public static final Disruptor<SmsEvent> disruptor = new Disruptor<>(
            factory,
            bufferSize,
            new SmsEventThreadFactory(),
            ProducerType.SINGLE, // 单生产者或多生产者
            new SleepingWaitStrategy() // 等待策略
    );

    // 10. 获取环形缓冲区
    public static final RingBuffer<SmsEvent> ringBuffer = disruptor.getRingBuffer();

    static {
        // 8. 注册事件处理器
        WorkEventHandler workEventHandler = new WorkEventHandler();
        int thread = 1;
        List<WorkEventHandler> list = new ArrayList<>();
        for (int i = 0; i < thread; i++) {
            list.add(workEventHandler);
        }
        disruptor.handleEventsWithWorkerPool(list.toArray(new WorkEventHandler[0]));

        // 9. 启动Disruptor
        disruptor.start();
    }

    public static void main(String[] args) {
        int size = 200;
        List<String> msgList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            msgList.add(RandomUtil.randomString(10));
        }

        MONITOR_EXECUTOR.submit(() -> {
            while (true) {
                long cursor = disruptor.getRingBuffer().getCursor();
                long min = disruptor.getRingBuffer().getMinimumGatingSequence();
                long count = cursor - min;
                log.info("未消费数量 >>> {}", count);
//                if (count == 0) {
//                    disruptor.shutdown();
//                    EXECUTOR.shutdown();
//                    return;
//                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        });


        for (String s : msgList) {
            long sequence = ringBuffer.next();
            try {
                SmsEvent event = ringBuffer.get(sequence);
                event.setMsg(s);
            } finally {
                ringBuffer.publish(sequence); // 发布事件
            }
        }
        log.info("发布完成 >>> ");
    }

    // 3. 事件处理器
    static class SmsEventHandler implements EventHandler<SmsEvent> {
        @Override
        public void onEvent(SmsEvent event, long sequence, boolean endOfBatch) {
            try {
                log.info("处理数据 >>> {}", event.getMsg());
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }


    static class WorkEventHandler implements WorkHandler<SmsEvent> {
        @Override
        public void onEvent(SmsEvent event) throws Exception {
            try {
                log.info("处理数据 >>> {}", event.getMsg());
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    static class SmsEventThreadFactory implements ThreadFactory {

        private final AtomicInteger counter = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            String name = "thread-" + counter.getAndIncrement();
            log.info("构建线程--- {}", name);
            Thread thread = new Thread(r, name);
            return thread;
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

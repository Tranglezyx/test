package com.test.util.thread.pool.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/28 17:56
 * @Description:
 */
@Slf4j
public class FastHandlerThread extends Thread{

    public void run(){
        int sleepTime = 100;
        log.info("睡眠--- {}ms",sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

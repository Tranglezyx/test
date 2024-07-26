package com.test.basic;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/7/26 10:08
 * @Description:
 */
public class ShutdownHookApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始---");
        Thread.sleep(3000);
        Runtime.getRuntime().addShutdownHook(new Thread(ShutdownHookApp::shutdownHookRun));
        while(true){

        }
    }


    public static void shutdownHookRun(){
        System.out.println("停机---");
    }
}

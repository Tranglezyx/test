package com.test.thread.ReentrantLock;

/**
 * @author trangle
 */
public class SynchronizeApp {

    private volatile int val = 1;

    private void printAndIncrease() {
        System.out.println(Thread.currentThread().getName() + " prints " + val);
        val++;
    }

    // print 1,2,3 7,8,9
    public class PrinterA implements Runnable {
        @Override
        public void run() {
            while (val <= 3) {
                printAndIncrease();
            }

            // print 1,2,3 then notify printerB
            synchronized (SynchronizeApp.this) {
                System.out.println("PrinterA printed 1,2,3; notify PrinterB");
                SynchronizeApp.this.notify();
            }

            try {
                while (val <= 6) {
                    synchronized (SynchronizeApp.this) {
                        System.out.println("wait in printerA");
                        SynchronizeApp.this.wait();
                    }
                }
                System.out.println("wait end printerA");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (val <= 9) {
                printAndIncrease();
            }
            System.out.println("PrinterA exits");
        }
    }
    // print 4,5,6 after printA print 1,2,3
    public class PrinterB implements Runnable {

        @Override
        public void run() {
            while (val < 3) {
                synchronized (SynchronizeApp.this) {
                    try {
                        System.out
                                .println("printerB wait for printerA printed 1,2,3");
                        SynchronizeApp.this.wait();
                        System.out
                                .println("printerB waited for printerA printed 1,2,3");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            while (val <= 6) {
                printAndIncrease();
            }

            System.out.println("notify in printerB");
            synchronized (SynchronizeApp.this) {
                SynchronizeApp.this.notify();
            }
            System.out.println("notify end printerB");
            System.out.println("PrinterB exits.");
        }
    }
    public static void main(String[] args) {
        SynchronizeApp demo = new SynchronizeApp();
        demo.doPrint();
    }

    private void doPrint() {
        PrinterA pa = new PrinterA();
        PrinterB pb = new PrinterB();
        Thread a = new Thread(pa);
        a.setName("printerA");
        Thread b = new Thread(pb);
        b.setName("printerB");
        // 必须让b线程先执行，否则b线程有可能得不到锁，执行不了wait，而a线程一直持有锁，会先notify了
        b.start();
        a.start();
    }
}

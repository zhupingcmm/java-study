package com.hero.sychronizedTest;

import java.util.concurrent.CountDownLatch;

/**
 * 静态方法：锁的类对象，锁的是该类所有实例
 */
public class SyncThread1 {
    private volatile static int count;

    public static synchronized void increment() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 100;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            Thread t = new Thread(() -> {
                try {
                    countDownLatch.await();
                    SyncThread1.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
            countDownLatch.countDown();
        }
        Thread.sleep(1000);
        System.out.println(SyncThread1.count);
    }
}

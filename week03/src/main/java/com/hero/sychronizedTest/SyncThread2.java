package com.hero.sychronizedTest;

import java.util.concurrent.CountDownLatch;

/**
 * 普通方法： 锁的是实例对象
 */
public class SyncThread2 {
    private volatile int count =0;
    public synchronized void increment() {count ++;}

    public static void main(String[] args) throws InterruptedException {
        int size = 100;
        SyncThread2 syncThread2 = new SyncThread2();
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try{
                    countDownLatch.await();
                    syncThread2.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            countDownLatch.countDown();
        }
        Thread.sleep(100);

        System.out.println("count is :" +  syncThread2.count);
    }

}

package com.hero.sychronizedTest;

import java.util.concurrent.CountDownLatch;

public class SyncThread3 {
    Object lock = new Object();
    private volatile static int count = 0;
    public void increment() {
        synchronized (lock){
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 100;
        SyncThread3 syncThread3= new SyncThread3();
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try{
                    countDownLatch.await();
                    syncThread3.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            countDownLatch.countDown();
        }
        Thread.sleep(100);

        System.out.println("count is :" +  syncThread3.count);
    }
}

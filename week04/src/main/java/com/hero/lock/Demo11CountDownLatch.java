package com.hero.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo11CountDownLatch {
    public static void main(String[] args) {
        int size = 6;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);

                    System.out.println(Thread.currentThread().getName() + "下班，离开公司");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "程序员" + i).start();

        }

        new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + "下班，离开公司");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "程序员卷王").start();
    }
}

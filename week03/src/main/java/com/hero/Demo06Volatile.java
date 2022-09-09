package com.hero;

import java.util.concurrent.CountDownLatch;

public class Demo06Volatile {
    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();

        int size = 3;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < 3; i++) {
           Thread t = new Thread(() -> {
               try {
                   countDownLatch.await();
                   demo.run();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           });
           t.start();
//           t.join();
           countDownLatch.countDown();
        }

        Thread.sleep(1000);
        System.out.println("count = "+demo.count);
    }

    static class VolatileDemo implements Runnable {
        public volatile int count;
        //public volatile AtomicInteger count = new AtomicInteger(0);

        public void run() {
            addCount();
        }

        public void addCount() {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }
    }
}

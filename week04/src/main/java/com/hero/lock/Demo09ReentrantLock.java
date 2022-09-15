package com.hero.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo09ReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(demo);
            t.start();
        }

        Thread.sleep(1000);
        System.out.println("count++" + demo.count);
    }

    static class VolatileDemo implements Runnable{
        public int count = 0;


        private final Lock lock = new ReentrantLock();

        @Override
        public void run() {
            addCount();
        }

        private void addCount() {
            lock.lock();
            for (int i = 0; i < 100; i++) {
                count++;
            }
            lock.unlock();
        }
    }
}

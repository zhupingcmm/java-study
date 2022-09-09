package com.hero;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo07Atomic {
    public static void main(String[] args) throws InterruptedException {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(atomicDemo);
            t.start();

        }
        Thread.sleep(1000);
        System.out.println(atomicDemo.count.get());
    }

    static class AtomicDemo implements Runnable {

        public final AtomicInteger count = new AtomicInteger(0);
        @Override
        public void run() {
            addCount();
        }

        private void addCount(){
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet();
            }
        }
    }
}

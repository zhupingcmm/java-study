package com.hero.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Demo13CyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("===召唤神龙===");
        });

        for (int i = 0; i < 7 ; i++) {
            final  int temInt = i;
            new Thread(() -> {

                try {
                    System.out.println(Thread.currentThread().getName() + "\t 收集到第" + temInt + "颗龙珠");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "\t 第" + temInt + "颗龙珠,飞走了");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, "Thread" + i).start();
        }
    }
}

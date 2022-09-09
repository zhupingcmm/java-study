package com.hero;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Demo08ABA {
    static AtomicInteger ai = new AtomicInteger(100);
    static AtomicStampedReference<Integer> asi = new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("========ABA问题的产生==========");
        Thread t1 = new Thread(() -> {
            ai.compareAndSet(100, 101);
            ai.compareAndSet(101,100);
        }, "t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = ai.compareAndSet(100, 2022);
            System.out.println(b + "\t" +ai.get());
        }, "t2");
        t2.start();
        t1.join();
        t2.join();

        System.out.println("========ABA问题的解决==========");

        new Thread(() -> {
            int stamp = asi.getStamp();

            System.out.println(Thread.currentThread().getName() + "\t第一次版本号:" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            asi.compareAndSet(100, 101, asi.getStamp(), asi.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第二次版本号:" + asi.getStamp());

            asi.compareAndSet(101, 100, asi.getStamp(), asi.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第三次版本号:" + asi.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int stamp = asi.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号： " + stamp);
            //休眠3s
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            //1A  --> 2B --> 3A
            boolean result= asi.compareAndSet(100,2022, stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t修改成功与否："+result+"  当前最新版本号"+ asi.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t当前实际值："+ asi.getReference());
        }, "t4").start();
    }


}

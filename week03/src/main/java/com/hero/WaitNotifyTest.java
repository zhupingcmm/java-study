package com.hero;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

public class WaitNotifyTest <T>{
    private LinkedList<T> queue = new LinkedList<>();

    public synchronized void put(T val) throws InterruptedException {
        if (queue.size() >=5) {
            System.out.println("Producer: queue is full!!!");
            this.wait();
        }
        System.out.println("Producer: insert" + val +" to the queue");
        queue.offer(val);
        this.notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        if (queue.isEmpty()) {
            System.out.println("Consumer: queue is full!!!");
            this.wait();
        }

        T element = queue.poll();
        System.out.println("Consumer: consume a element " + element + " from the queue");
        this.notifyAll();
        return element;
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyTest<Integer> waitNotifyTest = new WaitNotifyTest<>();
        int size = 2;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        Thread producerThread = new Thread(() -> {
           countDownLatch.countDown();
            for (int i = 0; i <10 ; i++) {
                try {
                    waitNotifyTest.put(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            countDownLatch.countDown();
            for (int i = 0; i < 10; i++) {
                try {
                    int val =  waitNotifyTest.get();
                    System.out.println("get val: " + val);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producerThread.start();
        consumerThread.start();
        countDownLatch.await();
        producerThread.join();
        consumerThread.join();
        System.out.println("end");
    }

}

package com.hero.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Demo10ReadWriteLock {
    private static volatile int count = 0;
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        WriteDemo writeDemo = new WriteDemo(lock.writeLock());

        ReadDemo readDemo = new ReadDemo(lock.readLock());
        for (int i = 0; i < 3; i++) {
            new Thread(writeDemo).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(readDemo).start();
        }


    }

    static class ReadDemo implements Runnable {

        private ReentrantReadWriteLock.ReadLock readLock;

        public ReadDemo(ReentrantReadWriteLock.ReadLock readLock){
            this.readLock = readLock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                readLock.lock();
                System.out.println("读锁: " + count);
                readLock.unlock();
            }
        }
    }
    static class WriteDemo implements Runnable{

        private ReentrantReadWriteLock.WriteLock writeLock;

        public WriteDemo(ReentrantReadWriteLock.WriteLock writeLock){
            this.writeLock = writeLock;
        }


        @Override
        public void run() {


            for (int i = 0; i <5 ; i++) {

                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                writeLock.lock();
                count ++;
                System.out.println("写锁：" + count);
                writeLock.unlock();
            }

        }


    }
}

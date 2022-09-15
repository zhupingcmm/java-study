package com.hero.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo14Condition {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                shareData.wash();
            }
        },"洗头妹").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                shareData.cut();
            }
        }, "理发师").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                shareData.blow();
            }
        },"吹头发").start();
    }

    static class ShareData {

        private static volatile int number = 1;
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        public void wash ()  {
            lock.lock();
            try {
                while (number != 1) {
                    c1.await();
                }

                System.out.println(Thread.currentThread().getName() + ": wash");
                number = 2;

                c2.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }


        }

        public void cut() {
            lock.lock();
            try {
                while (number != 2) {
                    c2.await();
                }

                System.out.println(Thread.currentThread().getName() + ": cut");
                number =3;
                c3.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }

        public void blow () {
            lock.lock();
            try {
                while (number != 3){
                    c3.await();
                }

                System.out.println(Thread.currentThread().getName() + ": blow");
                number = 1;
                c1.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }
    }
}

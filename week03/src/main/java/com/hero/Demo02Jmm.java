package com.hero;

public class Demo02Jmm {
    public static void main(String[] args) throws InterruptedException {
        JmmDemo demo = new JmmDemo();
        Thread t = new Thread(demo);
        t.start();
        Thread.sleep(100);
        demo.flag = false;
        System.out.println("已经修改为false");
        System.out.println(demo.flag);

    }

    static class JmmDemo implements Runnable {
        public volatile boolean flag = true;
        private Object lock = new Object();
        @Override
        public void run() {
            System.out.println("子线程开始执行...");
            while (flag){
                synchronized (lock) {

                }
            }

            System.out.println("子线程结束");

        }
    }
}

package com.hero;

public class Demo05JmmVolatile {
    public static void main(String[] args) throws InterruptedException {
        JmmDemo jmmDemo = new JmmDemo();
        Thread t = new Thread(jmmDemo);
        t.start();
        Thread.sleep(100);
        jmmDemo.flag = false;
        System.out.println("已经修改为false");
        System.out.println(jmmDemo.flag);
    }

    static class JmmDemo implements Runnable{

        public volatile boolean flag = true;
        @Override
        public void run() {
            System.out.println("子线程执行...");
            while (flag) {

            }
            System.out.println("子线程结束。。。");
        }

    }
}

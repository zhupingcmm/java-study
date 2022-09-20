package com.mf.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo16ArrayBlockingQueue {
    private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) {
        Interviewer r1 = new Interviewer(queue);//面试官
        Engineers e2 = new Engineers(queue);//程序员们
        new Thread(r1).start();
        new Thread(e2).start();

    }

    static class Interviewer implements Runnable {

        private ArrayBlockingQueue<String> queue;
        public Interviewer (ArrayBlockingQueue queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            System.out.println("面试官: 我准备好了，可以开始面试了");
            String msg;
            try {
                while (!(msg = queue.take()).equals("stop")){
                    System.out.println(msg + " 面试 + 开始。。。");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(msg + "面试-结束");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    static class Engineers implements Runnable {

        private ArrayBlockingQueue<String> queue;
        public Engineers (ArrayBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String candidate = "程序员" + i;
                try {
                    queue.put(candidate);
                    System.out.println(candidate+" 就坐=等待面试~");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                queue.put("stop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

}

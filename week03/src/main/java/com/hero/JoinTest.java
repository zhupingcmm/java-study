package com.hero;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        JoinThread joinThread = new JoinThread();
        for (int i = 0; i <10 ; i++) {
            System.out.println("main thread: " + i);
            if (i ==3) {
                joinThread.start();
            }
            if (i ==5) {
                joinThread.join();
            }
        }

        System.out.println("end");
    }


    static class JoinThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

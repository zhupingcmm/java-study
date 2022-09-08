package com.hero;

public class Demo01Ticket {
    public static void main(String[] args) {
        SellTicketTask ticketTask = new SellTicketTask();
        Thread t1 = new Thread(ticketTask, "窗口1");
        Thread t2 = new Thread(ticketTask, "窗口2");
        Thread t3 = new Thread(ticketTask, "窗口3");
        t1.start();
        t2.start();
        t3.start();

    }

    static class SellTicketTask implements Runnable {
        private volatile int tickets =100;

        private final Object lock = new Object();

        @Override
        public void run() {
            while (true) {
//                synchronized (lock) {
                    if (tickets > 0) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println(Thread.currentThread().getName() + "-正在卖：" + tickets--);
                    }
//                }

            }
        }
    }
}

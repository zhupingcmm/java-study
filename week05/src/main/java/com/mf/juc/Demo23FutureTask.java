package com.mf.juc;

import java.util.concurrent.*;

public class Demo23FutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(futureTask);
        System.out.println("task result::" + futureTask.get());

        System.out.println("=======");
        executorService.shutdown();

    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");
            Thread.sleep(3000);

            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum +=i;
            }

            return sum;
        }
    }
}

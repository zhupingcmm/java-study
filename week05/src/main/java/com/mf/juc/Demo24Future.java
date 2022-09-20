package com.mf.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo24Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        new Thread(futureTask).start();
        System.out.println("计算结果:" + futureTask.get());
    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000);

            return 1;
        }
    }

}

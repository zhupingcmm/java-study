package com.mf.juc;

import java.util.Random;
import java.util.concurrent.*;

public class Demo22Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        Future<Integer> future =executorService.submit(new CallableTask());
        System.out.println(future.get());
        executorService.shutdown();

    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random(10).nextInt();
        }
    }
}

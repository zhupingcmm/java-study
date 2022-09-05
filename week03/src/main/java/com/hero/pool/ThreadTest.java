package com.hero.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(100);
           return "zp";
        });

        Thread thread = new Thread(futureTask);

        thread.start();

        String result = futureTask.get();

        System.out.println(result);

        System.out.println("end");
    }
}

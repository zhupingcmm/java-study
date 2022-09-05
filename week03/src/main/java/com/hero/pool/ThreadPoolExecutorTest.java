package com.hero.pool;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static ThreadPoolExecutor getPool () {
        return new ThreadPoolExecutor(
                5, 10,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = getPool();
//        poolExecutor.execute(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("zp");
//        });

//        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "OOOO";
//            }
//        });
//        Thread t1 = new Thread(futureTask);
//        String result = futureTask.get();
//        System.out.println(result);
        Future<String> future = poolExecutor.submit(() -> {
            Thread.sleep(10000);
            return "zp";
        });
        System.out.println(future.get());
        System.out.println("zppppp");
        poolExecutor.execute(()-> System.out.println("cmm"));
        poolExecutor.shutdown();
    }
}

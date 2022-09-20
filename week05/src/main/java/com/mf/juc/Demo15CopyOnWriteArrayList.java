package com.mf.juc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo15CopyOnWriteArrayList {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> tempList = Arrays.asList(1,2);
        CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(tempList);

        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(new ReadThread(copyList));
        service.execute(new WriteThread(copyList));
        service.execute(new WriteThread(copyList));
        service.execute(new WriteThread(copyList));
        service.execute(new ReadThread(copyList));

        service.execute(new WriteThread(copyList));
        service.execute(new ReadThread(copyList));
        service.execute(new WriteThread(copyList));


        TimeUnit.SECONDS.sleep(2);
        System.out.println("copy list size:" + copyList.size());
        service.shutdown();
    }

    static class ReadThread implements Runnable{

        private List<Integer> list;

        public ReadThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            System.out.println("size:=" + list.size() + ",::");
            for (Integer ele : list) {
                System.out.println(ele + ",");
            }
            System.out.println();
        }
    }

    static class WriteThread implements Runnable {

        private List<Integer> list;

        public WriteThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            this.list.add(9);
        }
    }
}

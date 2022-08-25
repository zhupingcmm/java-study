package com.hero.jvm.memory;

public class HeapDemo {
    public static void main(String[] args) {

        System.out.println("===========start============");

        try {
            Thread.sleep(10000000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===========end============");
    }
}

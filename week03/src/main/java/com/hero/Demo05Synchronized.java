package com.hero;

public class Demo05Synchronized {
    public synchronized void increase() {
        System.out.println("synchronized METHOD");
    }

    public void syncBlock() {
        synchronized (this) {
            System.out.println("synchronized BLOCK");
        }
    }
}

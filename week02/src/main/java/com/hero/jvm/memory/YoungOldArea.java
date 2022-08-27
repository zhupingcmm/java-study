package com.hero.jvm.memory;

public class YoungOldArea {
    public static void main(String[] args) {
        byte [] buffer = new byte[1024*1024*20];
        System.out.println(buffer);
    }
}

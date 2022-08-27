package com.hero.jvm.memory;

public class MemoryAllocationGuarantee {
    private static final int size = 1024 * 1024;

    public static void memoryAllocation(){
        byte[] b1, b2, b3, b4;
        b1 = new byte[1 * size];
        b2 = new byte[1 * size];
        b3 = new byte[1 * size];
        b4 = new byte[5 * size];

        System.out.println("done");
    }

    public static void main(String[] args) {
        memoryAllocation();
    }
}

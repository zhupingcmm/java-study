package com.hero.jvm.memory;

import java.nio.ByteBuffer;

public class ByteBufferCompare {
    private final static int TIME = 1000000;

    public static void allocateCompare() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < TIME; i++) {
            //申请堆内存
            ByteBuffer.allocate(2);
        }
        long end = System.currentTimeMillis();
        System.out.println("在进行" + TIME + "次分配操作时，堆内存 分配耗时:" +
                (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < TIME; i++) {
            //申请直接内存
            ByteBuffer.allocateDirect(2);
        }
        end = System.currentTimeMillis();
        System.out.println("在进行" + TIME + "次分配操作时，直接内存 分配耗时:" +
                (end - start) + "ms");
    }

    public static void operatorCompare() {
        //申请堆内存
        ByteBuffer byteBuffer = ByteBuffer.allocate(2 * TIME);
        long start = System.currentTimeMillis();

        for (int i = 0; i < TIME; i++) {
            byteBuffer.putChar('a');
        }

        byteBuffer.flip();

        for (int i = 0; i < TIME; i++) {
            byteBuffer.getChar();
        }
        long end = System.currentTimeMillis();
        System.out.println("在进行" + TIME + "次读写操作时，非直接内存读写耗时：" +
                (end - start) + "ms");

        byteBuffer = ByteBuffer.allocateDirect(2* TIME);
        start = System.currentTimeMillis();
        for (int i = 0; i < TIME; i++) {
            byteBuffer.putChar('a');
        }
        byteBuffer.flip();

        for (int i = 0; i < TIME; i++) {
            byteBuffer.getChar();
        }

        end = System.currentTimeMillis();

        System.out.println("在进行" + TIME + "次读写操作时，直接内存读写耗时：" +
                (end - start) + "ms");
    }

    public static void main(String[] args) {
        allocateCompare();
        operatorCompare();
    }
}

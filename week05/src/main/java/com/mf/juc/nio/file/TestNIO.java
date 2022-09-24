package com.mf.juc.nio.file;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNIO {

    @Test
    public void test1() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(new File("basic.txt"));

        FileChannel fileChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        String str = "HelloJava";
        buffer.put(str.getBytes());

        buffer.flip();

        fileChannel.write(buffer);

        fileChannel.close();
        outputStream.close();

    }

    @Test
    public void test2() throws IOException {
        File file = new File("basic.txt");
        FileInputStream inputStream = new FileInputStream(file);

        FileChannel fileChannel = inputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());

        fileChannel.read(buffer);

        System.out.println(new String(buffer.array()));
        fileChannel.close();
        inputStream.close();
    }
}

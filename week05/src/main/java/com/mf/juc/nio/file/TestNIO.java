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

    @Test
    public void test3() throws IOException {
        FileInputStream fis = new FileInputStream("basic.txt");
        FileOutputStream fos = new FileOutputStream("basic2.txt");
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }

        fis.close();
        fos.close();
    }


    @Test
    public void test4() throws IOException {
        FileInputStream fis = new FileInputStream("basic2.txt");
        FileOutputStream fos = new FileOutputStream("basic3.txt");
        FileChannel s = fis.getChannel();
        FileChannel d = fos.getChannel();
        d.transferFrom(s, 0, s.size());
        fis.close();
        fos.close();
    }
}

package com.mf.juc.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        while (true){
            //创建socket对象
            Socket socket = new Socket("127.0.0.1", 9999);
            // 2 从连接中获取输出流，并且发生消息
            OutputStream os  = socket.getOutputStream();
            System.out.println("请输入：");
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            os.write(msg.getBytes());

            // 3 从连接中获取输入流， 接收消息
            InputStream in = socket.getInputStream();//阻塞
            byte[]b = new byte[20];
            in.read(b);
            System.out.println("老板说：" + new String(b).trim());
            socket.close();
        }
    }
}

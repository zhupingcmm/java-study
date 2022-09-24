package com.mf.juc.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端 启动....");
        System.out.println("初始化端口 9999 ");
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            // 监听客户端
           Socket socket = serverSocket.accept();//阻塞
            // 从连接中取出输入流来接收消息
            InputStream inputStream = socket.getInputStream();//阻塞
            byte [] bytes = new byte[20];
            inputStream.read(bytes);
            String clintIP = socket.getInetAddress().getHostAddress();
            System.out.println(clintIP + "说:" + new String(bytes).trim());

            //4.从连接中取出输出流并回话
            OutputStream os = socket.getOutputStream();
            System.out.println("请输入：");
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            os.write(msg.getBytes());
            //5.关闭
            socket.close();
        }
    }
}

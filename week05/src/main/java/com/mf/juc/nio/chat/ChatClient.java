package com.mf.juc.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ChatClient {
    private final String HOST = "localhost";
    private int PORT = 9999;
    private SocketChannel socketChannel;
    private String userName;

    public ChatClient() throws IOException {
        socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress(HOST, PORT);

        if (!socketChannel.connect(address)){
            while (!socketChannel.finishConnect()){
                System.out.println("client：服务器好像有点忙，咱也别闲着了，去搞点兼职吧");
            }
        }

        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println("---------------Client(" + userName + ") is ready---------------");
    }

    public void sendMsg(String msg) throws IOException {
        if (msg.equalsIgnoreCase("bye")){
            socketChannel.close();
            return;
        }

        msg = userName + "说" + msg;
        socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }

    public void receiveMsg () throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int size = socketChannel.read(buffer);
        if (size > 0) {
            String msg = new String(buffer.array());
            System.out.println(msg.trim());
        }
    }
}

package com.mf.juc.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);

        if (!channel.connect(address)){
            while (!channel.finishConnect()){
                System.out.println("client: 连接金莲的同时，可以做的别的事情");
            }
        }

        String msg = "你好， 金莲，大朗在家吗?";
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(buffer);

        System.in.read();
    }
}

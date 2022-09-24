package com.mf.juc.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 1. 开启 ServerSocketChannel 通道 对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2. 开启一个 Selector 选择器
        Selector selector = Selector.open();

        //3. 绑定端口号9999
        System.out.println("服务端 启动....");
        System.out.println("初始化端口 9999 ");
        serverSocketChannel.bind(new InetSocketAddress(9999));

        // 4. 配置阻塞方式
        serverSocketChannel.configureBlocking(false);
        // 5. selector 上注册 ServerSocketChannel，绑定连接操作
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 6. 循环执行：监听连接事件及读取事件

        while (true) {
            // 6.1 监控客户端连接
            // selector.select() 方法返回的是客户端通道数，如果通道数为0，说明没有客户端连接
            if (selector.select(2000) == 0) { // 非阻塞
                System.out.println("Server：门庆没有找我，去找王妈妈搞点兼职做~");
                continue;
            }

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    System.out.println("OP_ACCEPT");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("client发来的数据:" + new String(buffer.array()));
                }
            }

            keyIterator.remove();
        }
    }
}

package com.mf.juc.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ChatServer {
    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private int PORT = 9999;

    public static void main(String[] args) throws Exception {
        new ChatServer().start();;
    }

    public ChatServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        printInfo("真人网络聊天室 启动.......");
        printInfo("真人网络聊天室 初始化端口 9999.......");
        printInfo("真人网络聊天室 初始化网络ip地址 121.199.163.228.......");
    }

    public void start() throws IOException {
        while (true){
            if (selector.select(2000) == 0){
                System.out.println("Server:没有客户端连接，我去搞点兼职");
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isAcceptable()){
                    SocketChannel socketChannel  = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println(socketChannel.getRemoteAddress().toString().substring(1) + "上线了");
                }

                if(key.isReadable()){
                    readMsg(key);
                }

                //一定要把当前key删掉，防止重复处理
                iterator.remove();
            }
        }
    }

    public void readMsg (SelectionKey key) throws IOException {
       SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count =0;
        try {
            count = socketChannel.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (count > 0) {
            String msg = new String(buffer.array());
            printInfo(msg);

            broadCast(socketChannel,msg );

        }
    }

    public void broadCast(SocketChannel socketChannel, String msg) throws IOException {
        System.out.println("服务器广播消息");
        for (SelectionKey key : selector.selectedKeys()) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel) {

                ((SocketChannel) channel).write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }
    private void printInfo(String str) { //往控制台打印消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("[" + sdf.format(new Date()) + "] -> " + str);
    }
}

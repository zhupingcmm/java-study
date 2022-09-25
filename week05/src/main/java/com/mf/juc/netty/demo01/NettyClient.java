package com.mf.juc.netty.demo01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {

        // 1. 创建一个线程组
        EventLoopGroup group = new NioEventLoopGroup();

        // 2. 创建客户端启动助手，完成相关配置
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group) // 3. 设置线程组
                .channel(NioSocketChannel.class) // 4. 设置客户端通道实现类
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 6. 往Pipeline 链中添加自定义的handler
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });

        System.out.println("*********客户端 准备就绪 msg 发射**********");

        //7. 启动客户端去链接服务端 connect 方法是异步的 sync 方法是同步阻塞的
        ChannelFuture cf = bootstrap.connect("127.0.0.1", 9999).sync();

        //8. 关闭链接
        cf.channel().closeFuture().sync();
    }
}

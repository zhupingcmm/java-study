package com.mf.juc.netty.demo01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });
        System.out.println("......服务端 启动中 init port:9999 ......");
        ChannelFuture cf=bootstrap.bind(9999).sync();  //10. 绑定端口 bind方法是异步的  sync方法是同步阻塞的
        System.out.println("......服务端 启动成功 ......");

        //11. 关闭通道，关闭线程组
        cf.channel().closeFuture().sync(); //异步
        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}

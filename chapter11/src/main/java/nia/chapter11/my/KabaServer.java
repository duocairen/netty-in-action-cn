package nia.chapter11.my;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import nia.chapter11.IdleStateHandlerInitializer;

import java.net.InetSocketAddress;

/**
 * TmpServer
 *
 * @author hank
 * @create 2017-07-18 9:54
 **/
public class KabaServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(6666))
                    .childHandler(
                            new KabaServerInitializer()
                    );
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("server start listen on "+future.channel().localAddress());
            future.channel().closeFuture().sync();
        }finally {
            parentGroup.shutdownGracefully().sync();
            childGroup.shutdownGracefully().sync();
        }



    }
}

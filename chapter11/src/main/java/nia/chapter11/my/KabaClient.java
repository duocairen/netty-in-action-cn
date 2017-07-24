package nia.chapter11.my;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * TmpClient
 *
 * @author hank
 * @create 2017-07-18 15:26
 **/
public class KabaClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("127.0.0.1", 6666))
                    .handler(
                            new KabaClientInitializer()
                    );
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
            System.out.println("关闭客户端完成");
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}

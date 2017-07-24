package nia.chapter11;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * TestHttpAggregator
 *
 * @author hank
 * @create 2017-05-23 10:31
 **/
public class TestHttpAggregator {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group).localAddress(6666).channel(NioServerSocketChannel.class).childHandler(new HttpAggregatorInitializer(false));
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("服务器开始监听6666端口");
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
            System.out.println("服务器端已退出");
        }
    }
}

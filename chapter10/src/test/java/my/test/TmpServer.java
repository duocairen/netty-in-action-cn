package my.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import nia.chapter10.FourByteToStringDecoder;
import nia.chapter10.IntegerConsumer;
import nia.chapter10.StringToIntegerDecoder;
import nia.chapter10.ToIntegerDecoder;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * TmpServer
 *
 * @author hank
 * @create 2017-07-18 9:54
 **/
public class TmpServer {

    public static void main(String[] args) throws Exception {
        final IntegerConsumer integerConsumer = new IntegerConsumer();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(6666))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new FourByteToStringDecoder());
                            ch.pipeline().addLast(new StringToIntegerDecoder());
                            ch.pipeline().addLast("integerConsumer", integerConsumer);
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("server start listen on "+future.channel().localAddress());
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }



    }
}

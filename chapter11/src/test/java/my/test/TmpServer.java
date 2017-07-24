package my.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import nia.chapter11.CmdHandlerInitializer;
import nia.chapter11.HttpAggregatorInitializer;
import nia.chapter11.IdleStateHandlerInitializer;

import java.net.InetSocketAddress;

/**
 * TmpServer
 *
 * @author hank
 * @create 2017-07-18 9:54
 **/
public class TmpServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(6666))
                    .childHandler(
                           // new CmdHandlerInitializer()
                            new HttpAggregatorInitializer(false)
                           // new IdleStateHandlerInitializer()
                    );
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("server start listen on "+future.channel().localAddress());
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }



    }
}

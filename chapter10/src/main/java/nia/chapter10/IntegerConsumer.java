package nia.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * IntegerConsumer
 *
 * @author hank
 * @create 2017-07-18 10:06
 **/
public class IntegerConsumer extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer i = (Integer) msg;
        System.out.println("收到一个int: "+i);
        int b =++i;
        byte[] bytes = (b+"").getBytes();
        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
        ctx.writeAndFlush(byteBuf);
    }
}

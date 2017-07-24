package nia.chapter9;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * MyOutputHandler
 *
 * @author hank
 * @create 2017-05-22 16:04
 **/
public class MyLogHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(this.getClass().getName()+"处理数据："+msg);
        ctx.fireChannelRead(msg);
    }
}

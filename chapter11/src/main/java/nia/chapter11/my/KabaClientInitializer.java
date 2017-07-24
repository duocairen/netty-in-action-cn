package nia.chapter11.my;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;
import nia.chapter11.ExceptionHandler;

/**
 * KabaClientInitializer
 *
 * @author hank
 * @create 2017-07-18 15:59
 **/
public class KabaClientInitializer extends ChannelInitializer{

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new KabaEncoder());
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024*1024, 0, 4, 0, 4));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new KabaDecoder());
        pipeline.addLast(new KabaClientLogicHandler());
        pipeline.addLast(new ExceptionHandler());
    }
}

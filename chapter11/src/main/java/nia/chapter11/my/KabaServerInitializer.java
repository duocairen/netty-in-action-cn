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
 * KabaInitializer
 *
 * @author hank
 * @create 2017-07-18 15:39
 **/
public class KabaServerInitializer extends ChannelInitializer{

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //输出之前添加长度字段
        pipeline.addLast(new LengthFieldPrepender(4));
        //输出之前转成ByteBuf
        pipeline.addLast(new KabaEncoder());
        //下边全部是输入Handler，先按长度截取包，然后由ByteBuf转成字符串，再由字符串转成Kaba对象实体
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024*1024, 0, 4, 0, 4));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new KabaDecoder());
        pipeline.addLast(new KabaServerLogicHandler());
        //异常处理器一般放最后
        pipeline.addLast(new ExceptionHandler());

    }
}

package nia.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * MyIntegerDecoder
 *
 * @author hank
 * @create 2017-05-22 16:22
 **/
public class MyAbsIntegerDecoder  extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() >= 4) {
            int v= Math.abs(in.readInt());
            System.out.println(this.getClass().getName()+"处理数据："+v);
            out.add(v);
        }
    }
}

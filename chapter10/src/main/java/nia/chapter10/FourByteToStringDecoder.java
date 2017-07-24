package nia.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * FourByteToStringDecoder
 *
 * @author hank
 * @create 2017-07-18 10:37
 **/
public class FourByteToStringDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()>=4) {
            ByteBuf byteBuf = in.readBytes(4);
            String str =  byteBuf.toString(CharsetUtil.UTF_8);
            out.add(str);
            byteBuf.release();
        }
    }
}

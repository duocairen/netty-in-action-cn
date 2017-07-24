package nia.chapter10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * StringToIntegerDecoder
 *
 * @author hank
 * @create 2017-07-18 10:42
 **/
public class StringToIntegerDecoder extends MessageToMessageDecoder<String>{
    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        int i = Integer.parseInt(msg);
        out.add(i);
    }
}

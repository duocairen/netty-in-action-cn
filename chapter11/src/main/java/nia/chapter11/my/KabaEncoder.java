package nia.chapter11.my;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import me.hao0.common.json.Jsons;

/**
 * KabaEncoder
 *
 * @author hank
 * @create 2017-07-18 15:31
 **/
public class KabaEncoder extends MessageToByteEncoder<Kaba>{

    @Override
    protected void encode(ChannelHandlerContext ctx, Kaba msg, ByteBuf out) throws Exception {
        String kabaStr = Jsons.DEFAULT.toJson(msg);
        byte[] bytes = kabaStr.getBytes(CharsetUtil.UTF_8);
        //不再手工添加长度字段，改用LengthFieldPrepender
        //out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}

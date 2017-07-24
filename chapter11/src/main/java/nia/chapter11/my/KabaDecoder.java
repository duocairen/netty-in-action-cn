package nia.chapter11.my;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import me.hao0.common.json.Jsons;

import java.util.List;

/**
 * KabaDecoder a decoder for kaba protocol.
 *
 * @author hank
 * @create 2017-07-18 15:31
 **/
/*public class KabaDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //ByteBuf readBuf =  in.readBytes(in.readableBytes());
        //String kabaStr = readBuf.toString(CharsetUtil.UTF_8);
        //Kaba kaba = Jsons.DEFAULT.fromJson(kabaStr, Kaba.class);
        //out.add(kaba);
        //readBuf.release();
        String kabaStr = in.toString(CharsetUtil.UTF_8);
        in.readerIndex(in.readableBytes());
        Kaba kaba  = Jsons.DEFAULT.fromJson(kabaStr, Kaba.class);
        out.add(kaba);
    }
}*/
/*
public class KabaDecoder extends MessageToMessageDecoder<ByteBuf>{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //ByteBuf readBuf =  in.readBytes(in.readableBytes());
        //String kabaStr = readBuf.toString(CharsetUtil.UTF_8);
        //Kaba kaba = Jsons.DEFAULT.fromJson(kabaStr, Kaba.class);
        //out.add(kaba);
        //readBuf.release();
        String kabaStr = in.toString(CharsetUtil.UTF_8);
        //in.readerIndex(in.readableBytes());
        Kaba kaba  = Jsons.DEFAULT.fromJson(kabaStr, Kaba.class);
        out.add(kaba);
    }
}*/


public class KabaDecoder extends MessageToMessageDecoder<String>{
    @Override
    protected void decode(ChannelHandlerContext ctx, String in, List<Object> out) throws Exception {
        Kaba kaba  = Jsons.DEFAULT.fromJson(in, Kaba.class);
        out.add(kaba);
    }
}

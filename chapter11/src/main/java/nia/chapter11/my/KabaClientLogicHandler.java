package nia.chapter11.my;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.hao0.common.json.Jsons;

/**
 * KabaClientLogicHandler
 *
 * @author hank
 * @create 2017-07-18 15:57
 **/
public class KabaClientLogicHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //连接成功后客户端发送消息给服务器端
        for(int i=0; i<20; i++) {
            Kaba kaba = new Kaba();
            kaba.setVersion(1);
            kaba.setTid("transaction"+i);
            kaba.setCode(0);
            kaba.setBody("is it success?");
            kaba.setUrl("question");
            System.out.println("客户端发送请求：");
            System.out.println(Jsons.DEFAULT.toJson(kaba));
            ctx.channel().writeAndFlush(kaba);
            ctx.fireChannelActive();
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Kaba recKaba = (Kaba) msg;
        System.out.println("客户端收到响应：");
        System.out.println(Jsons.DEFAULT.toJson(recKaba));

    }
}

package nia.chapter11.my;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.hao0.common.json.Jsons;

/**
 * KabaServerLogicHandler
 *
 * @author hank
 * @create 2017-07-18 15:42
 **/
public class KabaServerLogicHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Kaba recKaba = (Kaba) msg;
        System.out.println("服务端收到请求:");
        System.out.println(Jsons.DEFAULT.toJson(recKaba));
        Kaba response = new Kaba();
        response.setBody("success");
        response.setCode(1);
        response.setTid(recKaba.getTid());
        response.setVersion(1);
        System.out.println("服务端发出响应：");
        System.out.println(Jsons.DEFAULT.toJson(response));
        ctx.writeAndFlush(response);
    }
}

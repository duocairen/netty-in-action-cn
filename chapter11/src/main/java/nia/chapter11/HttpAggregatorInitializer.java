package nia.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


/**
 * 代码清单 11-3 自动聚合 HTTP 的消息片段
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {
    private final boolean isClient;

    public HttpAggregatorInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (isClient) {
            //如果是客户端，则添加 HttpClientCodec
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            //如果是服务器，则添加 HttpServerCodec
            pipeline.addLast("codec", new HttpServerCodec());
        }
        //将最大的消息大小为 512 KB 的 HttpObjectAggregator 添加到 ChannelPipeline
        pipeline.addLast("aggregator",
                new HttpObjectAggregator(512 * 1024));
        pipeline.addLast(new LogicHandler());
        pipeline.addLast(new ExceptionHandler());
        pipeline.addFirst(new RemindConnHandler());
    }


    public static final class LogicHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            boolean log = false;
            FullHttpRequest request = (FullHttpRequest) msg;

            if(log) {
                String body = request.content().toString(CharsetUtil.UTF_8);
                System.out.println("request path:" + request.uri());
                System.out.println("request method: " + request.method().name());
                HttpHeaders headers = request.headers();
                for (String name : headers.names()) {
                    System.out.println(name + "：" + headers.get(name));
                }
                System.out.println("request body:" + body);
            }
            //ctx.fireChannelRead(msg);
            String result = "{\"code\":0,\"desc\":\"success\"}";
            ByteBuf buf = Unpooled.copiedBuffer(result, CharsetUtil.UTF_8);
            HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
            response.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                    .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                    //记得添加content-length，否则浏览器端不知响应内容完毕一直等待
                    .set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());
            ctx.channel().writeAndFlush(response);
        }
    }
}

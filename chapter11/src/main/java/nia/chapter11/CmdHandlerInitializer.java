package nia.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 代码清单 11-9 使用 ChannelInitializer 安装解码器
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class CmdHandlerInitializer extends ChannelInitializer<Channel> {
    private static final byte SPACE = (byte)' ';

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LineBasedFrameDecoder(1024));
        //添加 CmdDecoder 以提取 Cmd 对象，并将它转发给下一个 ChannelInboundHandler
        pipeline.addLast(new CmdDecoder());
        //添加 CmdHandler 以接收和处理 Cmd 对象
        pipeline.addLast(new CmdHandler());
        //添加异常处理器
        pipeline.addLast(new ExceptionHandler());
    }

    //Cmd POJO
    public static final class Cmd {
        private final ByteBuf name;
        private final ByteBuf args;

        public Cmd(ByteBuf name, ByteBuf args) {
            this.name = name;
            this.args = args;
        }

        public ByteBuf name() {
            return name;
        }

        public ByteBuf args() {
            return args;
        }
    }

    public static final class CmdDecoder extends MessageToMessageDecoder<ByteBuf> {
        @Override
        public void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
            String cmdStr = msg.toString(CharsetUtil.UTF_8);
            String[] strs = cmdStr.split(" ");
            Cmd cmd = new Cmd(Unpooled.copiedBuffer(strs[0], CharsetUtil.UTF_8), Unpooled.copiedBuffer(strs[1], CharsetUtil.UTF_8));
            out.add(cmd);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println(ctx.channel().remoteAddress()+"连接上服务器了");
            ctx.fireChannelActive();
        }
    }

        /*@Override
        protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer)
            throws Exception {
            //从 ByteBuf 中提取由行尾符序列分隔的帧
            ByteBuf frame = (ByteBuf) super.decode(ctx, buffer);
            if(frame != null) {
                System.out.println(frame.toString(CharsetUtil.UTF_8));
            }
            if (frame == null) {
                //如果输入中没有帧，则返回 null

                return null;
            }
            //查找第一个空格字符的索引。前面是命令名称，接着是参数
            int index = frame.indexOf(frame.readerIndex(),
                    frame.writerIndex(), SPACE);
            //使用包含有命令名称和参数的切片创建新的 Cmd 对象
            System.out.println("CmdDecoder生成Cmd对象完毕");
            return new Cmd(Unpooled.copiedBuffer("a", CharsetUtil.UTF_8), Unpooled.copiedBuffer("b", CharsetUtil.UTF_8));
        }*/


    public static final class CmdHandler
        extends SimpleChannelInboundHandler<Cmd> {
        @Override
        public void channelRead0(ChannelHandlerContext ctx, Cmd msg)
            throws Exception {
            // Do something with the command
            //处理传经 ChannelPipeline 的 Cmd 对象
            System.out.println("CmdHandler运行命令：" + msg.name().toString(CharsetUtil.UTF_8)+"；参数："+msg.args().toString(CharsetUtil.UTF_8));
            //ctx.fireChannelRead(msg);
            String echo = "\r\n execute success\r\n";
            ctx.writeAndFlush(Unpooled.copiedBuffer(echo, CharsetUtil.UTF_8));
        }
    }
}

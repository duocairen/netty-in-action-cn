package nia.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * TestAll
 *
 * @author hank
 * @create 2017-05-22 17:24
 **/
public class TestAll {

    @Test
    public void testCmd() {
        EmbeddedChannel channel = new EmbeddedChannel(new CmdHandlerInitializer());
        ByteBuf buf = Unpooled.copiedBuffer("ls -lh\r\njstat -gcutil", CharsetUtil.UTF_8);
        Assert.assertTrue(channel.writeInbound(buf));
        Assert.assertTrue(channel.finish());

        CmdHandlerInitializer.Cmd cmd = channel.readInbound();
        System.out.println(cmd.name().toString(CharsetUtil.UTF_8));
        //CmdHandlerInitializer.Cmd cmd2 = channel.readInbound();
        //System.out.println(cmd2.name().toString(CharsetUtil.UTF_8));

    }
}

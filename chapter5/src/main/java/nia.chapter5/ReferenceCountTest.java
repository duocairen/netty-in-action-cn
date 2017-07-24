package nia.chapter5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.junit.Test;

/**
 * ReferenceCountTest
 *
 * @author hank
 * @create 2017-05-22 11:28
 **/
public class ReferenceCountTest {

    @Test
    public void testCount() {
        ByteBuf buf = Unpooled.copiedBuffer("this is a buffer", CharsetUtil.UTF_8);
        ByteBuf shallow = buf.duplicate();
        shallow.setChar(0, 'T');
        System.out.println(buf.toString(CharsetUtil.UTF_8));
    }
}

package cn.eiden.hsm.net.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * MessagePack编码器
 * @author Eiden J.P Zhou
 * @date 2020/9/17 16:19
 */
public class MessagePackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        //messagePack序列化工具
        MessagePack messagePack = new MessagePack();
        // 序列化
        byte[] write = messagePack.write(msg);
        //写入缓冲区
        out.writeBytes(write);
    }
}

package cn.eiden.hsm.cockpit.java;

import cn.eiden.hsm.net.api.PlayRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

/**
 * @author Eiden J.P Zhou
 * @date 2020/9/22 15:15
 */
@Slf4j
public class HearthServerHandler extends ChannelInboundHandlerAdapter {
    private UserPipeline userPipeline;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("初始化成功");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (this.userPipeline != null){
            this.userPipeline.getInputQueue().add(msg.toString().replaceAll("\"",""));
        }else {
            //检查
            if (msg instanceof Value){
                Value playRequest = (Value) msg;
                MessagePack messagePack = new MessagePack();
                PlayRequest convert = messagePack.convert(playRequest, PlayRequest.class);
                synchronized (this){
                    //获取到锁再次检查
                    if (this.userPipeline == null){
                        UserPipeline userPipeline = new UserPipeline(convert.getName(), ctx);
                        this.userPipeline = userPipeline;
                        Register.getInstance().registered(userPipeline);
                    }
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warn(cause.getMessage());
        ctx.close();
    }
}

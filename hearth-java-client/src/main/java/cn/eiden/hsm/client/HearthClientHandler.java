package cn.eiden.hsm.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端处理类
 *
 * @author Eiden J.P Zhou
 * @date 2020/9/23 10:08
 */
@Slf4j
public class HearthClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===已连接至服务器===");
        HearthClientApplication.ctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = msg.toString();
        log.info(message.replaceAll("\n","\r"));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务器网络中断");
        ctx.close();
    }
}

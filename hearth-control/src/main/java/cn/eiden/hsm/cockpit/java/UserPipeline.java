package cn.eiden.hsm.cockpit.java;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用户TCP管道
 * @author Eiden J.P Zhou
 * @date 2020/9/23 11:00
 */
public class UserPipeline implements Runnable{
    private String name;
    private ChannelHandlerContext ctx;
    private BlockingQueue<String> inputQueue;
    private BlockingQueue<String> outputQueue;


    public UserPipeline(String name, ChannelHandlerContext ctx) {
        this.name = name;
        this.ctx = ctx;
        this.inputQueue = new LinkedBlockingQueue<>();
        this.outputQueue = new LinkedBlockingQueue<>();
    }

    public String getName() {
        return name;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public BlockingQueue<String> getInputQueue() {
        return inputQueue;
    }

    public BlockingQueue<String> getOutputQueue() {
        return outputQueue;
    }

    @Override
    public void run() {
        for (;;){
            try {
                String take = outputQueue.take();
                ctx.writeAndFlush(take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

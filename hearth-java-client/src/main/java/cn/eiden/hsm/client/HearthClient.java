package cn.eiden.hsm.client;

import cn.eiden.hsm.net.msgpack.MessagePackDecoder;
import cn.eiden.hsm.net.msgpack.MessagePackEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 客户端
 * @author Eiden J.P Zhou
 * @date 2020/9/23 10:02
 */
public class HearthClient implements Runnable{
    /**服务器地址*/
    private String host;
    /**服务器端口*/
    private int port;

    @Override
    public void run() {
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("framework Decoder",
                                    new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                            pipeline.addLast("msgpack Decoder", new MessagePackDecoder());
                            pipeline.addLast("framework Encoder", new LengthFieldPrepender(2));
                            pipeline.addLast("msgpack Encoder", new MessagePackEncoder());
                            pipeline.addLast("client handler", new HearthClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            synchronized (HearthClientApplication.class){
                //Netty客户端启动完成唤醒主线程
                HearthClientApplication.class.notify();
            }
            channelFuture.channel().closeFuture().sync();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            work.shutdownGracefully();
        }
    }

    public HearthClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
}

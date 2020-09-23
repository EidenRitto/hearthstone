package cn.eiden.hsm.cockpit.java;

import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.net.msgpack.MessagePackDecoder;
import cn.eiden.hsm.net.msgpack.MessagePackEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author Eiden J.P Zhou
 * @date 2020/9/22 11:03
 */
public class HearthServer {

    public void run(int port) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("framework Decoder",
                                    new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                            pipeline.addLast("msgpack Decoder", new MessagePackDecoder());
                            pipeline.addLast("framework Encoder", new LengthFieldPrepender(2));
                            pipeline.addLast("msgpack Encoder", new MessagePackEncoder());
                            pipeline.addLast("diy handler", new HearthServerHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            //加载缓存
            CardFactory.getInstance();
            channelFuture.channel().closeFuture().sync();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}

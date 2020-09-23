package cn.eiden.hsm.client;

import cn.eiden.hsm.net.api.PlayRequest;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executors;

/**
 * 客户端启动类
 * @author Eiden J.P Zhou
 * @date 2020/9/23 10:23
 */
public class HearthClientApplication {
    private static String host;
    private static int port;
    public static ChannelHandlerContext ctx;

    public static void main(String[] args) throws Exception {
        //加载配置文件
        loadConfig();
        //启动TCP客户端连接至服务器
        HearthClient hearthClient = new HearthClient(host, port);
        Executors.newCachedThreadPool().execute(hearthClient);
        synchronized (HearthClientApplication.class){
            //阻塞主线程直到Netty客户端启动完成
            HearthClientApplication.class.wait();
        }

        System.out.println("请输入Id昵称");
        final String nico = getMessage();
        PlayRequest request = new PlayRequest(nico,"1");
        ctx.writeAndFlush(request);
        for (;;){
            String order = getMessage();
            ctx.writeAndFlush(order);
        }
    }

    private static void loadConfig() throws IOException {
        InputStream in = HearthClientApplication.class.getResourceAsStream("/config.properties");
        Properties p = new Properties();
        p.load(in);
        host = p.getProperty("hsm.host");
        port = Integer.parseInt(p.getProperty("hsm.port"));
    }


    private static String getMessage(){
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}

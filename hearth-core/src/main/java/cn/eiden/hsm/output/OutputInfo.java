package cn.eiden.hsm.output;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/1 10:55
 */
@Slf4j
public class OutputInfo {
    public static LinkedBlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    public static void info(String info){
        log.info(info);
        try {
            messageQueue.put(info);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void info(String format,Object... args){
        String str = String.format(format, args);
        info(str);
    }
}

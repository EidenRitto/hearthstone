package cn.eiden.hsm.output;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/1 10:55
 */
@Slf4j
public class OutputInfo {
    public static BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    public static void info(BlockingQueue<String> blockingQueue, String info) {
        if (blockingQueue == null){
            info(info);
            return;
        }
        log.info(info);
        try {
            blockingQueue.put(info);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void info(String info) {
        log.info(info);
        try {
            messageQueue.put(info);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void info(BlockingQueue<String> blockingQueue, String format, Object... args) {
        if (blockingQueue == null){
            info(format,args);
            return;
        }
        String str = String.format(format, args);
        info(blockingQueue, str);
    }

    public static void info(String format, Object... args) {
        String str = String.format(format, args);
        info(str);
    }
}

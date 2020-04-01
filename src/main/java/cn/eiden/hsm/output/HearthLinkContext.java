package cn.eiden.hsm.output;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/1 12:45
 */
public class HearthLinkContext implements Runnable {
    public static LinkedBlockingQueue<String> inputMessage = new LinkedBlockingQueue<>();
    @Override
    public void run() {

    }
}

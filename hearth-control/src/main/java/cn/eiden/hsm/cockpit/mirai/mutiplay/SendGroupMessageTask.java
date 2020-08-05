package cn.eiden.hsm.cockpit.mirai.mutiplay;

import cn.eiden.hsm.cockpit.coolq.MultiConfig;
import net.mamoe.mirai.Bot;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Eiden J.P Zhou
 * @date 2020/8/5 9:46
 */
public class SendGroupMessageTask implements Runnable {
    private MultiConfig multiConfig;
    private Bot bot;
    private AtomicBoolean start;

    @Override
    public void run() {
        BlockingQueue<String> messageQueue = multiConfig.getGroupMessageQueue();
        while (start.get()){
            try {
                String poll = messageQueue.poll(30, TimeUnit.SECONDS);
                if (poll == null){
                    continue;
                }
                bot.getGroup(multiConfig.getGroupId()).sendMessage(poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SendGroupMessageTask(MultiConfig multiConfig, Bot bot, AtomicBoolean start) {
        this.multiConfig = multiConfig;
        this.bot = bot;
        this.start = start;
    }
}

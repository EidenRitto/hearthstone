package cn.eiden.hsm.cockpit.mirai.mutiplay;

import cn.eiden.hsm.cockpit.coolq.User;
import net.mamoe.mirai.Bot;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Eiden J.P Zhou
 * @date 2020/8/5 9:43
 */
public class SendMessageTask implements Runnable {
    private User user;
    private Bot bot;
    private AtomicBoolean start;

    @Override
    public void run() {
        BlockingQueue<String> messageQueue = user.getMessageQueue();
        while (start.get()){
            try {
                String poll = messageQueue.poll(30, TimeUnit.SECONDS);
                if (poll == null){
                    continue;
                }
                bot.getFriend(user.getId()).sendMessage(poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SendMessageTask(User user, Bot bot, AtomicBoolean start) {
        this.user = user;
        this.bot = bot;
        this.start = start;
    }
}

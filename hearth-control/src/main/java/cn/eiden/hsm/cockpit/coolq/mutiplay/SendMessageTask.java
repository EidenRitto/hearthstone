package cn.eiden.hsm.cockpit.coolq.mutiplay;

import cc.moecraft.icq.sender.IcqHttpApi;
import cn.eiden.hsm.cockpit.coolq.User;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 发送消息线程
 * @author Eiden J.P Zhou
 * @date 2020/6/5 8:54
 */
public class SendMessageTask implements Runnable {
    private User user;
    private IcqHttpApi icqHttpApi;
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
                icqHttpApi.sendPrivateMsg(user.getId(),poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SendMessageTask(User user, IcqHttpApi icqHttpApi, AtomicBoolean start) {
        this.user = user;
        this.icqHttpApi = icqHttpApi;
        this.start = start;
    }
}

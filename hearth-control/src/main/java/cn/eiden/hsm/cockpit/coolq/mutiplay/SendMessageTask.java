package cn.eiden.hsm.cockpit.coolq.mutiplay;

import cc.moecraft.icq.sender.IcqHttpApi;
import cn.eiden.hsm.cockpit.coolq.User;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;

/**
 * 发送消息线程
 * @author 周晋平
 * @date 2020/6/5 8:54
 */
public class SendMessageTask implements Runnable {
    private User user;
    private IcqHttpApi icqHttpApi;
    @Setter
    private boolean start = true;

    @Override
    public void run() {
        BlockingQueue<String> messageQueue = user.getMessageQueue();
        while (start){
            try {
                String take = messageQueue.take();
                icqHttpApi.sendPrivateMsg(user.getId(),take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SendMessageTask(User user, IcqHttpApi icqHttpApi) {
        this.user = user;
        this.icqHttpApi = icqHttpApi;
    }
}

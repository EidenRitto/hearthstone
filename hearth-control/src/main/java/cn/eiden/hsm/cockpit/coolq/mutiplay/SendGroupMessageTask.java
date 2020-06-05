package cn.eiden.hsm.cockpit.coolq.mutiplay;

import cc.moecraft.icq.sender.IcqHttpApi;
import cn.eiden.hsm.cockpit.coolq.MultiConfig;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;

/**
 * 发送消息线程
 * @author Eiden J.P Zhou
 * @date 2020/6/5 8:54
 */
public class SendGroupMessageTask implements Runnable {
    private MultiConfig multiConfig;
    private IcqHttpApi icqHttpApi;
    @Setter
    private boolean start = true;

    @Override
    public void run() {
        BlockingQueue<String> messageQueue = multiConfig.getGroupMessageQueue();
        while (start){
            try {
                String take = messageQueue.take();
                icqHttpApi.sendGroupMsg(multiConfig.getGroupId(),take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SendGroupMessageTask(MultiConfig multiConfig, IcqHttpApi icqHttpApi) {
        this.multiConfig = multiConfig;
        this.icqHttpApi = icqHttpApi;
    }
}

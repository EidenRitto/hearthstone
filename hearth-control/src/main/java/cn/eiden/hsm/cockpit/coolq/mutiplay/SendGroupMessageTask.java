package cn.eiden.hsm.cockpit.coolq.mutiplay;

import cc.moecraft.icq.sender.IcqHttpApi;
import cn.eiden.hsm.cockpit.coolq.MultiConfig;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 发送消息线程
 * @author Eiden J.P Zhou
 * @date 2020/6/5 8:54
 */
public class SendGroupMessageTask implements Runnable {
    private MultiConfig multiConfig;
    private IcqHttpApi icqHttpApi;
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
                icqHttpApi.sendGroupMsg(multiConfig.getGroupId(),poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SendGroupMessageTask(MultiConfig multiConfig, IcqHttpApi icqHttpApi, AtomicBoolean start) {
        this.multiConfig = multiConfig;
        this.icqHttpApi = icqHttpApi;
        this.start = start;
    }
}

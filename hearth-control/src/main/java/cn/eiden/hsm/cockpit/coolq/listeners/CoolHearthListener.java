package cn.eiden.hsm.cockpit.coolq.listeners;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import cn.eiden.hsm.output.HearthLinkContext;
import cn.eiden.hsm.output.OutputInfo;
import cn.eiden.hsm.test.GameDemo;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 14:42
 */
public class CoolHearthListener extends IcqListener {
    private static final String HEARTH = "炉石传说";

    private boolean isStart = false;

    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @EventHandler
    public void onEvent(EventPrivateMessage privateMessage) {
        response(privateMessage);
    }

    private void response(EventMessage eventMessage){
        String message = eventMessage.getMessage();
        String[] order = message.split(" ");
        if (message.contains(HEARTH) && order.length>1){
            final String deckStr = order[1];
            cachedThreadPool.execute(new ReadMessageWriteTask(eventMessage));
            cachedThreadPool.execute(() ->{
                GameDemo gameDemo = new GameDemo();
                try {
                    gameDemo.start(deckStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            isStart = true;
        }else {
            if (isStart){
                try {
                    HearthLinkContext.inputMessage.put(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static class ReadMessageWriteTask implements Runnable {
        private EventMessage eventMessage;

        public ReadMessageWriteTask(EventMessage eventMessage) {
            this.eventMessage = eventMessage;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true){
                String message = OutputInfo.messageQueue.take();
                eventMessage.respond(message);
            }
        }
    }
}

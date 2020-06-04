package cn.eiden.hsm.cockpit.coolq.listeners;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import cn.eiden.hsm.cockpit.coolq.HearthOrderConstant;
import cn.eiden.hsm.GameDemo;
import cn.eiden.hsm.cockpit.coolq.MultiConfig;
import cn.eiden.hsm.cockpit.coolq.User;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 15:45
 */
public class HearthMultiplayerListener extends IcqListener {

    private Long organizerId ;
    private Long responderId ;

    private Long groupId;

    private boolean isGameStart = false;

    private String organizerDeckStr;
    private String responderDeckStr;

    private MultiConfig multiConfig;

    @EventHandler
    public void onEvent(EventGroupMessage groupMessage) {
        String message = groupMessage.getMessage();
        if (message.equals(HearthOrderConstant.HEARTH_MULTIPLE) && organizerId == null){
            groupMessage.respondPrivateMessage(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1_1);
            groupMessage.respond(String.format(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1,groupMessage.getSender().getInfo().getNickname()));
            organizerId = groupMessage.getSenderId();
            groupId = groupMessage.getGroupId();
        }
    }

    @EventHandler
    public void onEvent2(EventGroupMessage groupMessage) {
        String message = groupMessage.getMessage();
        if (organizerId != null && responderId == null && message.equals(HearthOrderConstant.AGREE)){
            groupMessage.respondPrivateMessage(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1_1);
            responderId = groupMessage.getSenderId();
            groupMessage.respond(String.format(HearthOrderConstant.HEARTH_MULTIPLE_STEP_2,groupMessage.getSender().getInfo().getNickname()));
        }
    }

    @EventHandler
    private void onOrganizer(EventPrivateMessage privateMessage){
        String message = privateMessage.getMessage();
        if (organizerId.equals(privateMessage.getSenderId())){
            if (organizerDeckStr != null){
                if (isGameStart){
                    try {
                        multiConfig.getUser1().getInputQueue().put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                if ("0".equals(message)){
                    organizerDeckStr = HearthOrderConstant.DECK_WARRIOR;
                } else if ("1".equals(message)) {
                    organizerDeckStr = HearthOrderConstant.DECK_MAGE;
                }
                checkBegin(privateMessage);
            }
        }
    }

    @EventHandler
    private void onResponder(EventPrivateMessage privateMessage){
        String message = privateMessage.getMessage();
        if (responderId.equals(privateMessage.getSenderId())){
            if (responderDeckStr != null){
                if (isGameStart){
                    try {
                        multiConfig.getUser2().getInputQueue().put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                if ("0".equals(message)){
                    responderDeckStr = HearthOrderConstant.DECK_WARRIOR;
                } else if ("1".equals(message)) {
                    responderDeckStr = HearthOrderConstant.DECK_MAGE;
                }
                checkBegin(privateMessage);
            }
        }
    }

    private void checkBegin(EventMessage eventMessage){
        if (responderDeckStr!=null && organizerDeckStr!=null){
            eventMessage.getHttpApi().sendGroupMsg(groupId,HearthOrderConstant.HEARTH_MULTIPLE_STEP_3);
            multiConfig = MultiConfig.builder()
                    .groupId(groupId)
                    .user1(User.builder()
                            .id(organizerId)
                            .deckStr(organizerDeckStr)
                            .messageQueue(new LinkedBlockingQueue<>())
                            .inputQueue(new LinkedBlockingQueue<>())
                            .build())
                    .user2(User.builder()
                            .id(responderId)
                            .deckStr(responderDeckStr)
                            .messageQueue(new LinkedBlockingQueue<>())
                            .inputQueue(new LinkedBlockingQueue<>())
                            .build())
                    .build();
            CoolHearthListener.cachedThreadPool.execute(() ->{
                GameDemo gameDemo = new GameDemo();
                try {
                    gameDemo.multiStart(organizerDeckStr,responderDeckStr,multiConfig);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            CoolHearthListener.cachedThreadPool.execute(() ->{

            });
            isGameStart = true;
        }
    }
}

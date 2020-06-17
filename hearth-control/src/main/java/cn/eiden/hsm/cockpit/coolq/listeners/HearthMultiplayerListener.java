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
import cn.eiden.hsm.cockpit.coolq.mutiplay.SendGroupMessageTask;
import cn.eiden.hsm.cockpit.coolq.mutiplay.SendMessageTask;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 15:45
 */
public class HearthMultiplayerListener extends IcqListener {
    /**
     * 发起者
     */
    private User organizerUser;
    /**
     * 响应者
     */
    private User responderUser;

    private boolean isGameStart = false;
    /**
     * 多人配置
     */
    private MultiConfig multiConfig;

    @EventHandler
    public void onEvent(EventGroupMessage groupMessage) {
        String message = groupMessage.getMessage();
        if (message.equals(HearthOrderConstant.HEARTH_MULTIPLE) && organizerUser == null) {
            groupMessage.respondPrivateMessage(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1_1);
            groupMessage.respond(String.format(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1, groupMessage.getSender().getInfo().getNickname()));
            organizerUser = new User();
            organizerUser.setId(groupMessage.getSenderId());
            organizerUser.setName(groupMessage.getSender().getInfo().getNickname());
            multiConfig = new MultiConfig();
            multiConfig.setGroupId(groupMessage.getGroupId());
            multiConfig.setOrganizer(organizerUser);
            multiConfig.setGroupMessageQueue(OutputInfo.messageQueue);
        }
    }

    @EventHandler
    public void onEvent2(EventGroupMessage groupMessage) {
        String message = groupMessage.getMessage();
        if (organizerUser != null &&
                responderUser == null &&
//                !groupMessage.getSenderId().equals(organizerUser.getId()) &&
                message.equals(HearthOrderConstant.AGREE)) {
            groupMessage.respondPrivateMessage(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1_1);
            responderUser = new User();
            responderUser.setId(groupMessage.getSenderId());
            responderUser.setName(groupMessage.getSender().getInfo().getNickname());
            multiConfig.setResponder(responderUser);
            groupMessage.respond(String.format(HearthOrderConstant.HEARTH_MULTIPLE_STEP_2, groupMessage.getSender().getInfo().getNickname()));
        }
    }

    @EventHandler
    public void onOrganizer(EventPrivateMessage privateMessage) {
        String message = privateMessage.getMessage();
        if (organizerUser != null && organizerUser.getId().equals(privateMessage.getSenderId())) {
            if (organizerUser.getDeckStr() != null) {
                if (isGameStart) {
                    try {
                        multiConfig.getOrganizer().getInputQueue().put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                chooseDeck(organizerUser, message);
                checkBegin(privateMessage);
            }
        }
    }

    private void chooseDeck(User user, String message) {
        if ("0".equals(message)) {
            user.setDeckStr(HearthOrderConstant.DECK_WARRIOR);
        } else if ("1".equals(message)) {
            user.setDeckStr(HearthOrderConstant.DECK_MAGE);
        } else if ("2".equals(message)) {
            user.setDeckStr(HearthOrderConstant.DECK_DEMONHUNTER);
        } else if ("3".equals(message)) {
            user.setDeckStr(HearthOrderConstant.DECK_DRUID);
        }
    }

    @EventHandler
    public void onResponder(EventPrivateMessage privateMessage) {
        String message = privateMessage.getMessage();
        if (responderUser != null && responderUser.getId().equals(privateMessage.getSenderId())) {
            if (responderUser.getDeckStr() != null) {
                if (isGameStart) {
                    try {
                        multiConfig.getResponder().getInputQueue().put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                chooseDeck(responderUser, message);
                checkBegin(privateMessage);
            }
        }
    }

    private void checkBegin(EventMessage eventMessage) {
        if (responderUser.isReady() && organizerUser.isReady()) {
            eventMessage.getHttpApi().sendGroupMsg(multiConfig.getGroupId(), HearthOrderConstant.HEARTH_MULTIPLE_STEP_3);
            CoolHearthListener.cachedThreadPool.execute(() -> {
                GameDemo gameDemo = new GameDemo();
                try {
                    gameDemo.multiStart(organizerUser.getDeckStr(), responderUser.getDeckStr(), multiConfig);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            CoolHearthListener.cachedThreadPool.execute(
                    new SendMessageTask(multiConfig.getOrganizer(), eventMessage.getHttpApi()));
            CoolHearthListener.cachedThreadPool.execute(
                    new SendMessageTask(multiConfig.getResponder(), eventMessage.getHttpApi()));
            CoolHearthListener.cachedThreadPool.execute(
                    new SendGroupMessageTask(multiConfig, eventMessage.getHttpApi()));
            isGameStart = true;
        }
    }
}

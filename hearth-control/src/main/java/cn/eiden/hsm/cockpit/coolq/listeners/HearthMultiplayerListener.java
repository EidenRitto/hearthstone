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
import cn.eiden.hsm.exception.GameOverException;
import cn.eiden.hsm.output.OutputInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 15:45
 */
public class HearthMultiplayerListener extends IcqListener {

    private Map<Long,MultiConfig> groupDictionary = new HashMap<>(16);
    private Map<Long,MultiConfig> userDictionary = new HashMap<>(16);

    @EventHandler
    public void onEvent(EventGroupMessage groupMessage) {
        if (userDictionary.containsKey(groupMessage.getSenderId())){
            return;
        }
        String message = groupMessage.getMessage();
        Long groupId = groupMessage.getGroupId();
        MultiConfig currConfig = groupDictionary.get(groupId);
        if (message.equals(HearthOrderConstant.HEARTH_MULTIPLE) && currConfig == null) {
            groupMessage.respondPrivateMessage(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1_1);
            groupMessage.respond(String.format(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1, groupMessage.getSender().getInfo().getNickname()));
            User organizerUser = new User();
            organizerUser.setId(groupMessage.getSenderId());
            organizerUser.setName(groupMessage.getSender().getInfo().getNickname());
            MultiConfig multiConfig = new MultiConfig();
            multiConfig.setGroupId(groupId);
            multiConfig.setOrganizer(organizerUser);
            multiConfig.setGroupMessageQueue(new LinkedBlockingQueue<>());
            this.groupDictionary.put(groupId,multiConfig);
            this.userDictionary.put(groupMessage.getSenderId(),multiConfig);
        }
    }

    @EventHandler
    public void onEventRespond(EventGroupMessage groupMessage) {
        if (userDictionary.containsKey(groupMessage.getSenderId())){
            return;
        }
        String message = groupMessage.getMessage();
        Long groupId = groupMessage.getGroupId();
        MultiConfig currConfig = groupDictionary.get(groupId);
        if (currConfig != null &&
                !currConfig.isGameStart() &&
                currConfig.getOrganizer()!= null &&
                currConfig.getResponder() == null &&
//                !groupMessage.getSenderId().equals(organizerUser.getId()) &&
                message.equals(HearthOrderConstant.AGREE)) {
            groupMessage.respondPrivateMessage(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1_1);
            User responderUser = new User();
            responderUser.setId(groupMessage.getSenderId());
            responderUser.setName(groupMessage.getSender().getInfo().getNickname());
            currConfig.setResponder(responderUser);
            groupMessage.respond(String.format(HearthOrderConstant.HEARTH_MULTIPLE_STEP_2, groupMessage.getSender().getInfo().getNickname()));
            this.userDictionary.put(groupMessage.getSenderId(),currConfig);
        }
    }

    @EventHandler
    public void onOrganizer(EventPrivateMessage privateMessage) {
        Long senderId = privateMessage.getSenderId();
        MultiConfig multiConfig = this.userDictionary.get(senderId);
        String message = privateMessage.getMessage();
        if (multiConfig.getOrganizer() != null && multiConfig.getOrganizer().getId().equals(senderId)) {
            if (multiConfig.getOrganizer().getDeckStr() != null) {
                if (multiConfig.isGameStart()) {
                    try {
                        multiConfig.getOrganizer().getInputQueue().put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                chooseDeck(multiConfig.getOrganizer(), message);
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
        Long senderId = privateMessage.getSenderId();
        MultiConfig multiConfig = this.userDictionary.get(senderId);
        String message = privateMessage.getMessage();
        if (multiConfig.getResponder() != null && multiConfig.getResponder().getId().equals(privateMessage.getSenderId())) {
            if (multiConfig.getResponder().getDeckStr() != null) {
                if (multiConfig.isGameStart()) {
                    try {
                        multiConfig.getResponder().getInputQueue().put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                chooseDeck(multiConfig.getResponder(), message);
                checkBegin(privateMessage);
            }
        }
    }

    private void checkBegin(EventMessage eventMessage) {
        Long senderId = eventMessage.getSenderId();
        MultiConfig multiConfig = this.userDictionary.get(senderId);
        if (multiConfig.getResponder().isReady() && multiConfig.getOrganizer().isReady()) {
            eventMessage.getHttpApi().sendGroupMsg(multiConfig.getGroupId(), HearthOrderConstant.HEARTH_MULTIPLE_STEP_3);
            CoolHearthListener.cachedThreadPool.execute(() -> {
                GameDemo gameDemo = new GameDemo();
                try {
                    gameDemo.multiStart(multiConfig.getOrganizer().getDeckStr(), multiConfig.getResponder().getDeckStr(), multiConfig);
                } catch (GameOverException goe){

                }catch (Exception e) {
                    e.printStackTrace();
                }
            });
            CoolHearthListener.cachedThreadPool.execute(
                    new SendMessageTask(multiConfig.getOrganizer(), eventMessage.getHttpApi()));
            CoolHearthListener.cachedThreadPool.execute(
                    new SendMessageTask(multiConfig.getResponder(), eventMessage.getHttpApi()));
            CoolHearthListener.cachedThreadPool.execute(
                    new SendGroupMessageTask(multiConfig, eventMessage.getHttpApi()));
            multiConfig.setGameStart(true);
        }
    }
}

package cn.eiden.hsm.cockpit.mirai.events;

import cn.eiden.hsm.GameDemo;
import cn.eiden.hsm.cockpit.coolq.HearthOrderConstant;
import cn.eiden.hsm.cockpit.coolq.MultiConfig;
import cn.eiden.hsm.cockpit.coolq.User;
import cn.eiden.hsm.cockpit.coolq.listeners.CoolHearthListener;
import cn.eiden.hsm.cockpit.mirai.mutiplay.SendGroupMessageTask;
import cn.eiden.hsm.cockpit.mirai.mutiplay.SendMessageTask;
import cn.eiden.hsm.exception.GameOverException;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.FriendMessageEvent;
import net.mamoe.mirai.message.GroupMessageEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Eiden J.P Zhou
 * @date 2020/8/4 17:40
 */
public class GroupEvent extends SimpleListenerHost {
    private Map<Long, MultiConfig> groupDictionary = new HashMap<>(16);
    private Map<Long,MultiConfig> userDictionary = new HashMap<>(16);

    @EventHandler
    public ListeningStatus onGroupMessage(GroupMessageEvent event) {
        this.handlerGroup(event);
        //保持监听
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus onPrivateMessage(FriendMessageEvent event) {
        this.handlerPrivate(event);
        //保持监听
        return ListeningStatus.LISTENING;
    }

    private void handlerGroup(GroupMessageEvent event){
        long sendId = event.getSender().getId();
        String nick = event.getSender().getNick();
        if (userDictionary.containsKey(sendId)){
            return;
        }
        String message = event.getMessage().contentToString();
        long groupId = event.getGroup().getId();
        MultiConfig currConfig = groupDictionary.get(groupId);

        //发起
        if (message.equals(HearthOrderConstant.HEARTH_MULTIPLE) && currConfig == null) {
            event.getSender().sendMessage(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1_1);
            event.getGroup().sendMessage(String.format(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1, nick));
            User organizerUser = new User();
            organizerUser.setId(sendId);
            organizerUser.setName(nick);
            MultiConfig multiConfig = new MultiConfig();
            multiConfig.setGroupId(groupId);
            multiConfig.setOrganizer(organizerUser);
            multiConfig.setGroupMessageQueue(new LinkedBlockingQueue<>());
            this.groupDictionary.put(groupId,multiConfig);
            this.userDictionary.put(sendId,multiConfig);
        }

        //响应
        if (currConfig != null &&
                !currConfig.getIsGameStart().get() &&
                currConfig.getOrganizer()!= null &&
                currConfig.getResponder() == null &&
//                !groupMessage.getSenderId().equals(organizerUser.getId()) &&
                message.equals(HearthOrderConstant.AGREE)) {
            event.getSender().sendMessage(HearthOrderConstant.HEARTH_MULTIPLE_STEP_1_1);
            User responderUser = new User();
            responderUser.setId(sendId);
            responderUser.setName(nick);
            currConfig.setResponder(responderUser);
            event.getGroup().sendMessage(String.format(HearthOrderConstant.HEARTH_MULTIPLE_STEP_2, nick));
            this.userDictionary.put(sendId,currConfig);
        }
    }


    private void handlerPrivate(FriendMessageEvent event){
        Long senderId = event.getSender().getId();
        MultiConfig multiConfig = this.userDictionary.get(senderId);
        String message = event.getMessage().contentToString();
        //发起
        if (multiConfig.getOrganizer() != null && multiConfig.getOrganizer().getId().equals(senderId)) {
            if (multiConfig.getOrganizer().getDeckStr() != null) {
                if (multiConfig.getIsGameStart().get()) {
                    try {
                        multiConfig.getOrganizer().getInputQueue().put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                chooseDeck(multiConfig.getOrganizer(), message);
                checkBegin(event);
            }
        }

        //响应
        if (multiConfig.getResponder() != null && multiConfig.getResponder().getId().equals(senderId)) {
            if (multiConfig.getResponder().getDeckStr() != null) {
                if (multiConfig.getIsGameStart().get()) {
                    try {
                        multiConfig.getResponder().getInputQueue().put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                chooseDeck(multiConfig.getResponder(), message);
                checkBegin(event);
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

    private void checkBegin(FriendMessageEvent event) {
        Long senderId = event.getSender().getId();
        MultiConfig multiConfig = this.userDictionary.get(senderId);
        if (multiConfig.getResponder().isReady() && multiConfig.getOrganizer().isReady()) {
            event.getBot().getGroup(multiConfig.getGroupId()).sendMessage(HearthOrderConstant.HEARTH_MULTIPLE_STEP_3);
            CoolHearthListener.cachedThreadPool.execute(() -> {
                GameDemo gameDemo = new GameDemo();
                try {
                    gameDemo.multiStart(multiConfig.getOrganizer().getDeckStr(), multiConfig.getResponder().getDeckStr(), multiConfig);
                } catch (GameOverException goe){
                    gameOver(multiConfig);
                }catch (Exception e) {
                    e.printStackTrace();
                    gameOver(multiConfig);
                }
            });
            multiConfig.getIsGameStart().set(true);
            CoolHearthListener.cachedThreadPool.execute(
                    new SendMessageTask(multiConfig.getOrganizer(), event.getBot(),multiConfig.getIsGameStart()));
            CoolHearthListener.cachedThreadPool.execute(
                    new SendMessageTask(multiConfig.getResponder(), event.getBot(),multiConfig.getIsGameStart()));
            CoolHearthListener.cachedThreadPool.execute(
                    new SendGroupMessageTask(multiConfig, event.getBot(),multiConfig.getIsGameStart()));
        }
    }


    private void gameOver(MultiConfig multiConfig) {
        multiConfig.getIsGameStart().lazySet(false);
        groupDictionary.remove(multiConfig.getGroupId());
        userDictionary.remove(multiConfig.getOrganizer().getId());
        userDictionary.remove(multiConfig.getResponder().getId());
    }
}

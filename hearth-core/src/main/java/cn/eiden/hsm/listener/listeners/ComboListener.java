package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;

/**
 * 随从连击监听
 * @author Eiden J.P Zhou
 * @date 2020/6/7 14:53
 */
public class ComboListener implements HearthListener {
    @EventHandler
    public void onEvent(UseMinionCardFromHandEvent event)
    {
        Minion minion = event.getMinionObject();
        Minion target = event.getTarget();
        //如果随从具有连击
        if (minion.hasCombo() && minion.getOwner().isComboTrigger()){
            event.getOwner().printPublicQueue(String.format("%s触发连击",minion.getMinionName()));
            minion.doCombo(target);
        }
    }
}

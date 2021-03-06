package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;

/**
 * 战吼监听
 * @author Eiden J.P Zhou
 * @date 2019/10/29 15:53
 */
public class BattleListener implements HearthListener {
    @EventHandler
    public void onEvent(UseMinionCardFromHandEvent event)
    {
        Minion minion = event.getMinionObject();
        Minion target = event.getTarget();
        if (minion.hasBattle()){
            event.getOwner().printPublicQueue(String.format("%s触发战吼",minion.getMinionName()));
            minion.doBattle(target);
        }
    }
}

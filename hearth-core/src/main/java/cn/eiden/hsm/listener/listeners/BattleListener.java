package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;

/**
 *
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
            minion.doBattle(event.getOwner(),target);
        }
    }
}

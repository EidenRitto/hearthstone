package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.MinionDeathEvent;
import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.listener.HearthListener;

/**
 * @author Eiden J.P Zhou
 * @date 2020/3/17 10:57
 */
public class DeathRattleListener implements HearthListener {

    @EventHandler
    public void onEvent(MinionDeathEvent event){
        Minion deathMinion = event.getDeathMinion();
        if (deathMinion instanceof DeathRattle){
            DeathRattle minion = (DeathRattle) deathMinion;
            minion.doDeathRattle();
        }
    }
}

package cn.eiden.hsm.game.card.base.shaman;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.EndTurnEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.shaman.HealingTotem;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;

import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/26 17:14
 */
public class HealingTotemCard extends HealingTotem {
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.getOwner().getEventManager().registerListener(new AfterTurnListener());
        return minion;
    }

    static class AfterTurnListener implements HearthListener {
        @EventHandler
        public void onEvent(EndTurnEvent endTurnEvent){
            Gamer owner = endTurnEvent.getOwner();
            List<Minion> minions = owner.getMinions();
            minions.forEach(e->e.recoveryHp(1));
        }
    }
}

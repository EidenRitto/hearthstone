package cn.eiden.hsm.game.card.base.shaman;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.EndTurnEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.shaman.HealingTotem;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;
import cn.eiden.hsm.listener.MinionListener;

import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/26 17:14
 */
public class HealingTotemCard extends HealingTotem {
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new AfterTurnListener());
        return minion;
    }

    public static class AfterTurnListener extends AbstractMinionListener implements MinionListener {
        @EventHandler
        public void onEvent(EndTurnEvent endTurnEvent){
            Gamer eventOwner = endTurnEvent.getOwner();
            if (eventOwner == getMinion().getOwner()){
                List<Minion> minions = eventOwner.getMinions();
                minions.forEach(e->e.recoveryHp(1));
            }
        }
    }
}

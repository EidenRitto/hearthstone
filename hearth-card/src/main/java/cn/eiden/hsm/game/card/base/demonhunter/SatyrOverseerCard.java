package cn.eiden.hsm.game.card.base.demonhunter;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.MinionAttackEvent;
import cn.eiden.hsm.game.card.defs.core.demonhunter.SatyrOverseer;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.hero.HeroMinion;
import cn.eiden.hsm.listener.AbstractMinionListener;

/**
 * 萨特监工
 * @author Eiden J.P Zhou
 * @date 2020/6/13 9:14
 */
public class SatyrOverseerCard extends SatyrOverseer {
    @Override
    public Minion createMinion() {
        return super.createMinion();
    }

    private static class SatyrListener extends AbstractMinionListener{
        @EventHandler
        public void onEvent(MinionAttackEvent event){
            Minion attackMinion = event.getAttackMinion();
            if (getMinion().getOwner() == attackMinion.getOwner() &&
                    attackMinion instanceof HeroMinion){
                getMinion().getOwner().addMinion(58496);
            }
        }
    }
}

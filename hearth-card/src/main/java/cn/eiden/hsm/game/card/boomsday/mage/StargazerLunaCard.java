package cn.eiden.hsm.game.card.boomsday.mage;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.MinionBeHurtEvent;
import cn.eiden.hsm.event.events.UseCardFromHandEvent;
import cn.eiden.hsm.game.card.defs.boomsday.mage.StargazerLuna;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.listener.AbstractMinionListener;
import cn.eiden.hsm.listener.MinionListener;

/**
 * 观星者露娜
 * @author Eiden J.P Zhou
 * @date 2020/6/4 11:14
 */
public class StargazerLunaCard extends StargazerLuna {
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new EffectListener());
        return minion;
    }
    static class EffectListener extends AbstractMinionListener implements MinionListener{
        @EventHandler
        public void onEvent(UseCardFromHandEvent event){
            if (event.getOwner() == getMinion().getOwner()){
                if (event.getOwner().getHand().isLastCard(event.getCard())){
                    event.getOwner().drawCard(1);
                }
            }
        }
    }
}

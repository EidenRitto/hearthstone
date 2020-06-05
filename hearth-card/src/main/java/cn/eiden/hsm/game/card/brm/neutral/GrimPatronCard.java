package cn.eiden.hsm.game.card.brm.neutral;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.MinionBeHurtEvent;
import cn.eiden.hsm.event.events.UseSecretCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.brm.neutral.GrimPatron;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;
import cn.eiden.hsm.listener.MinionListener;

import java.util.List;

/**
 * 恐怖的奴隶主
 * @author Eiden J.P Zhou
 * @date 2020/6/5 16:43
 */
public class GrimPatronCard extends GrimPatron {
    private static final int REF_ID = 2279;

    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new EffectListener());
        return minion;
    }

    static class EffectListener extends AbstractMinionListener implements MinionListener{
        @EventHandler
        public void onEvent(MinionBeHurtEvent event) {
            if (getMinion() == event.getMinion() && getMinion().getHealth() > 0){
                AbstractMinionCard cardById = (AbstractMinionCard) CardFactory.getCardById(REF_ID);
                assert cardById != null;
                Minion grimPatron = cardById.createMinion();
                getMinion().getOwner().addMinion(grimPatron);
            }
        }
    }
}

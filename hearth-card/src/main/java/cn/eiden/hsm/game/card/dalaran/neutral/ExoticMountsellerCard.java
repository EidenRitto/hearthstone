package cn.eiden.hsm.game.card.dalaran.neutral;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.event.events.UseSpellCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.dalaran.neutral.ExoticMountseller;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;

/**
 * 特殊坐骑商人
 * @author Eiden J.P Zhou
 * @date 2020/6/16 20:50
 */
public class ExoticMountsellerCard extends ExoticMountseller {
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new Listener());
        return minion;
    }

    private static class Listener extends AbstractMinionListener{
        @EventHandler
        public void onEvent(UseSpellCardFromHandEvent event){
            Gamer owner = getMinion().getOwner();
            if (owner == event.getOwner()){
                AbstractMinionCard card = (AbstractMinionCard) CardFactory.buildCard()
                        .cost(3)
                        .cardType(CardType.MINION)
                        .race(Race.BEAST)
                        .randomBuild();
                owner.addMinion(card.createMinion());
            }
        }
    }
}

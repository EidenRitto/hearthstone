package cn.eiden.hsm.game.card.gangs.mage;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.core.mage.Polymorph;
import cn.eiden.hsm.game.card.defs.gangs.mage.PotionOfPolymorph;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Secret;

/**
 * @author 周晋平
 * @date 2020/6/1 15:52
 */
public class PotionOfPolymorphCard extends PotionOfPolymorph {
    private static final int SHEEP_ID = 796;

    @Override
    public Secret createSecret() {
        return new PolymorphSecret();
    }

    static class PolymorphSecret extends AbstractSecret {

        @Override
        public boolean onSecret(Event event) {
            if (event.getClass() == triggerEvent()) {
                UseMinionCardFromHandEvent useMinionCardFromHandEvent = (UseMinionCardFromHandEvent) event;
                AbstractMinionCard sheepCard = (AbstractMinionCard) CardFactory.getCardById(SHEEP_ID);
                assert sheepCard != null;
                useMinionCardFromHandEvent.setMinionObject(sheepCard.createMinion());
                return true;
            }
            return false;
        }

        @Override
        public Class<? extends Event> triggerEvent() {
            return UseMinionCardFromHandEvent.class;
        }
    }
}

package cn.eiden.hsm.game.card.naxx.mage;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.MinionDeathEvent;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.naxx.mage.Duplicate;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Secret;
import cn.eiden.hsm.output.OutputInfo;

/**
 * 复制
 * @author Eiden J.P Zhou
 * @date 2020/6/2 9:48
 */
public class DuplicateCard extends Duplicate {
    @Override
    public Secret createSecret() {
        return new DuplicateSecret();
    }

    static class DuplicateSecret extends AbstractSecret{

        @Override
        public boolean onSecret(Event event) {
            if (event.getClass() == triggerEvent()){
                MinionDeathEvent minionDeathEvent = (MinionDeathEvent) event;
                if (getOwner() == minionDeathEvent.getOwner()){
                    OutputInfo.info("法师奥秘触发:复制");
                    String cardId = minionDeathEvent.getDeathMinion().getCardId();
                    Card card1 = CardFactory.getCardById(Integer.parseInt(cardId));
                    Card card2 = CardFactory.getCardById(Integer.parseInt(cardId));
                    getOwner().getHand().addHandsCard(card1);
                    getOwner().getHand().addHandsCard(card2);
                    return true;
                }
            }
            return false;
        }

        @Override
        public Class<? extends Event> triggerEvent() {
            return MinionDeathEvent.class;
        }
    }
}

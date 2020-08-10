package cn.eiden.hsm.game.card.sch.rogue;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.EndTurnEvent;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.rogue.Plagiarize;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Secret;

import java.util.List;

/**
 * 抄袭
 * @author Eiden J.P Zhou
 * @date 2020/8/10 14:56
 */
public class PlagiarizeCard extends Plagiarize {

    @Override
    public Secret createSecret() {
        return new PlagiarizeSecret();
    }

    public static class PlagiarizeSecret extends AbstractSecret {

        @Override
        public boolean onSecret(Event event) {
            if (event.getClass() == triggerEvent()) {
                EndTurnEvent endTurnEvent = (EndTurnEvent) event;
                List<Card> usedCardByTurnNum = endTurnEvent.getOwner().getHistory().getUsedCardByTurnNum(endTurnEvent.getOwner().getTurnNum());
                usedCardByTurnNum.stream()
                        .map(Card::getId)
                        .map(Integer::parseInt)
                        .map(CardFactory::getCardById)
                        .forEach(e -> endTurnEvent.getOwner().getEnemy().getHand().addHandsCard(e));
                event.getOwner().printPublicQueue("盗贼奥秘触发:抄袭");
                return true;
            }
            return false;
        }

        @Override
        public Class<? extends Event> triggerEvent() {
            return EndTurnEvent.class;
        }
    }
}

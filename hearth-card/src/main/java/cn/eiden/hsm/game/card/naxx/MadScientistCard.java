package cn.eiden.hsm.game.card.naxx;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.game.card.AbstractSecretCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.naxx.neutral.MadScientist;
import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.minion.Secret;

import java.util.Iterator;
import java.util.List;

/**
 * 疯狂的科学家
 * @author Eiden J.P Zhou
 * @date 2020/6/1 14:38
 */
public class MadScientistCard extends MadScientist {
    @Override
    protected DeathRattle selfDeathRattle() {
        return new MyDeathRattle();
    }

    static class MyDeathRattle implements DeathRattle{

        @Override
        public void doDeathRattle(GeneralItem minion) {
            Gamer owner = minion.getOwner();
            List<Card> deckCards = owner.getDeckCards();
            Iterator<Card> iterator = deckCards.iterator();
            while (iterator.hasNext()){
                Card deckCard = iterator.next();
                if (deckCard instanceof AbstractSecretCard){
                    Secret secret = ((AbstractSecretCard) deckCard).createSecret();
                    if (!owner.hasSecret(secret)){
                        owner.addSecret(secret);
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }
}

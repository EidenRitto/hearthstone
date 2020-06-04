package cn.eiden.hsm.game.history;

import cn.eiden.hsm.game.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 12:10
 */
public class HistoryImpl implements History {
    List<Card> usedCards = new ArrayList<>();

    @Override
    public void addUsedCard(Card card) {
        usedCards.add(card);
    }

    @Override
    public List<Card> getUsedCardWithType(Class<? extends Card> cardClass) {
        List<Card> result = new ArrayList<>();
        for (Card usedCard : usedCards) {
            if (usedCard.getClass() == cardClass){
                result.add(usedCard);
            }
        }
        return result;
    }
}

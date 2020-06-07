package cn.eiden.hsm.game.history;

import cn.eiden.hsm.game.card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 12:10
 */
public class HistoryImpl implements History {
    private Map<Integer, List<Card>> usedCardsMap;

    @Override
    public void addUsedCard(Card card, int turnNum) {
        if (usedCardsMap.containsKey(turnNum)) {
            usedCardsMap.get(turnNum).add(card);
        } else {
            List<Card> cardList = new ArrayList<>();
            cardList.add(card);
            usedCardsMap.put(turnNum, cardList);
        }
    }

    @Override
    public List<Card> getUsedCardWithType(Class<? extends Card> cardClass) {
        List<Card> result = new ArrayList<>();
        for (List<Card> cardList : usedCardsMap.values()) {
            for (Card card : cardList) {
                if (card.getClass() == cardClass) {
                    result.add(card);
                }
            }
        }
        return result;
    }

    @Override
    public int getUsedCardNumByTurnNum(int turnNum) {
        if (usedCardsMap.containsKey(turnNum)) {
            return usedCardsMap.get(turnNum).size();
        } else {
            return 0;
        }
    }

    public HistoryImpl() {
        this.usedCardsMap = new HashMap<>(32);
    }
}

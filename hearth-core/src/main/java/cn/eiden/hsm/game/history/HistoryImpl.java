package cn.eiden.hsm.game.history;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.minion.Minion;

import java.util.*;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 12:10
 */
public class HistoryImpl implements History {
    private Map<Integer, List<Card>> usedCardsMap;
    private List<Card> cardsHistory;

    @Override
    public void addUsedCard(Card card, int turnNum) {
        if (usedCardsMap.containsKey(turnNum)) {
            usedCardsMap.get(turnNum).add(card);
        } else {
            List<Card> cardList = new ArrayList<>();
            cardList.add(card);
            usedCardsMap.put(turnNum, cardList);
        }
        cardsHistory.add(card);
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

    @Override
    public int getCountByMinionName(Minion minion) {
        return Math.toIntExact(cardsHistory.stream().filter(e -> e.getCardName().equals(minion.getMinionName())).count());
    }

    public HistoryImpl() {
        this.usedCardsMap = new HashMap<>(32);
        this.cardsHistory = new ArrayList<>();
    }
}

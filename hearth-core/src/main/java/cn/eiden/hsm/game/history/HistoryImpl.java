package cn.eiden.hsm.game.history;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.minion.Minion;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 12:10
 */
public class HistoryImpl implements History,Cloneable {
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
    public List<Card> getUsedCardByTurnNum(int turnNum) {
        return usedCardsMap.get(turnNum);
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

    public void setUsedCardsMap(Map<Integer, List<Card>> usedCardsMap) {
        this.usedCardsMap = usedCardsMap;
    }

    public void setCardsHistory(List<Card> cardsHistory) {
        this.cardsHistory = cardsHistory;
    }

    @Override
    public History clone(){
        HistoryImpl history = new HistoryImpl();
        history.setCardsHistory(this.cardsHistory.stream().map(Card::clone).collect(Collectors.toList()));
        Map<Integer, List<Card>> map = new HashMap<>(32);
        for (Map.Entry<Integer, List<Card>> integerListEntry : usedCardsMap.entrySet()) {
            map.put(integerListEntry.getKey(),integerListEntry.getValue().stream().map(Card::clone).collect(Collectors.toList()));
        }
        history.setUsedCardsMap(map);
        return history;
    }
}

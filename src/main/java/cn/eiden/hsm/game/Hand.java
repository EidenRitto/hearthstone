package cn.eiden.hsm.game;

import cn.eiden.hsm.game.card.Card;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author 周晋平
 * @date 2020/3/27 17:29
 */
@Slf4j
public class Hand extends GameObject {
    private int handsLimit = 10;

    /**
     * 手牌
     */
    private List<Card> cards;

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 得到一张手牌
     */
    public void addHandsCard(Card card) {
        if (!isHandsFull()) {
            //如果手牌没满，添加到手牌中
            cards.add(card);
        } else {
            //手排满了，爆牌
            log.info(card.getCardName() + "因手牌满消失");
        }
    }

    /**
     * 失去一张手牌
     * @param cardIndex 手牌编号
     */
    public void loss(int cardIndex) {
        cards.remove(cardIndex);
    }

    /**
     * 手牌是否已满
     */
    public boolean isHandsFull() {
        return cards.size() >= handsLimit;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return cards;
    }
}

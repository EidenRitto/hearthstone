package cn.eiden.hsm.game;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.keyword.TwinSpell;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Eiden J.P Zhou
 * @date 2020/3/27 17:29
 */
@Slf4j
public class Hand extends AbstractGeneralItem implements Cloneable {

    private int handsLimit = 10;

    /**
     * 手牌
     */
    private List<Card> cards = new ArrayList<>();

    /**
     * @author : Eiden J.P Zhou
     * @date : 2018/9/13
     * 得到一张手牌
     */
    public void addHandsCard(Card card) {
        if (!isHandsFull()) {
            //如果手牌没满，添加到手牌中
            cards.add(card);
            card.setOwner(this.getOwner());
        } else {
            //手排满了，爆牌
            getOwner().printPublicQueue(card.getCardName() + "因手牌满消失");
        }
    }

    /**
     * 失去一张手牌
     *
     * @param cardIndex 手牌编号
     */
    public void loss(int cardIndex) {
        cards.remove(cardIndex);
    }

    public void loss(Card card) {
        cards.remove(card);
    }

    /**
     * 弃牌
     *
     * @param card 卡牌
     */
    public void drop(Card card) {
        getOwner().printPublicQueue(String.format("%s弃置%s", getOwner().getUserName(), card.getCardName()));
        this.loss(card);
    }

    public void used(Card card) {
        if (card instanceof TwinSpell) {
            int spellCopyId = ((TwinSpell) card).getSpellCopyId();
            Card copy = CardFactory.getCardById(spellCopyId);
            assert copy != null;
            copy.setOwner(getOwner());
            cards.set(cards.indexOf(card), copy);
        } else {
            this.loss(card);
        }
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

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * 卡牌是否是右手第一张
     *
     * @param card 卡牌
     * @return 右手第一张返回true
     */
    public boolean isLastCard(Card card) {
        return cards.get(cards.size() - 1) == card;
    }

    /**
     * 卡牌是否是左手第一张
     *
     * @param card 卡牌
     * @return 左手第一张返回true
     */
    public boolean isFirstCard(Card card) {
        return cards.get(0) == card;
    }

    @Override
    public Hand clone(){
        try {
            Hand clone = (Hand) super.clone();
            List<Card> cloneCards = cards.stream()
                    .map(Card::clone).collect(Collectors.toList());
            clone.setCards(cloneCards);
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

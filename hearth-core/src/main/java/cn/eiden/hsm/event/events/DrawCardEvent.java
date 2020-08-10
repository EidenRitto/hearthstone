package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;

/**
 * 抽取卡牌事件
 * @author Eiden J.P Zhou
 * @date 2020/8/10 10:10
 */
public class DrawCardEvent extends AbstractEvent {
    /**抽取的卡牌*/
    private Card card;

    public DrawCardEvent(Gamer owner, Card card) {
        super(owner);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}

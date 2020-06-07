package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.minion.Minion;
import lombok.Getter;
import lombok.Setter;

/**
 * 打出手牌事件
 *
 * @author Eiden J.P Zhou
 * @date 2020/6/4 11:28
 */
public class UseCardFromHandEvent extends AbstractEvent {
    @Setter
    @Getter
    private Card card;

    @Setter
    @Getter
    private Minion target;

    public UseCardFromHandEvent(Card card, Minion target) {
        super(card.getOwner());
        this.card = card;
        this.target = target;
    }
}

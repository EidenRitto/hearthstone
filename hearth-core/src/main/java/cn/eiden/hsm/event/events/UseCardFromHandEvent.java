package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import lombok.Getter;
import lombok.Setter;

/**
 * 打出手牌事件
 * @author Eiden J.P Zhou
 * @date 2020/6/4 11:28
 */
public class UseCardFromHandEvent extends AbstractEvent {
    @Setter
    @Getter
    private Card card;

    public UseCardFromHandEvent(Gamer owner, Card card) {
        super(owner);
        this.card = card;
    }
}

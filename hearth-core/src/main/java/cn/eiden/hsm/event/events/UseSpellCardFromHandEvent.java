package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.minion.Minion;
import lombok.Getter;
import lombok.Setter;

/**
 * 从手牌中打出法术事件
 * @author Eiden J.P Zhou
 * @date 2020/05/29 14:26
 */
@Getter
@Setter
public class UseSpellCardFromHandEvent extends AbstractEvent {
    private AbstractMagicCard magicCard;
    private Minion target;

    public UseSpellCardFromHandEvent(Gamer owner, AbstractMagicCard magicCard, Minion target) {
        super(owner);
        this.magicCard = magicCard;
        this.target = target;
    }
}

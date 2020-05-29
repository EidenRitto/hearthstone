package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractSecretCard;
import lombok.Getter;
import lombok.Setter;

/**
 * 从手牌中打出奥秘事件
 * @author Eiden J.P Zhou
 * @date 2020/05/29 14:26
 */
@Getter
@Setter
public class UseSecretCardFromHandEvent extends AbstractEvent {
    private AbstractSecretCard secretCard;

    public UseSecretCardFromHandEvent(Gamer owner, AbstractSecretCard secretCard) {
        super(owner);
        this.secretCard = secretCard;
    }
}

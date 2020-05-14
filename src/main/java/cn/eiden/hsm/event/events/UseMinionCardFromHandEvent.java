package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 从手牌中打出随从卡牌事件
 * @author Eiden J.P Zhou
 * @date 2019/10/29 14:26
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class UseMinionCardFromHandEvent extends AbstractEvent {
    private Minion minionObject;
    private Minion target;

    public UseMinionCardFromHandEvent(Gamer owner, Minion minionObject, Minion target) {
        super(owner);
        this.minionObject = minionObject;
        this.target = target;
    }
}

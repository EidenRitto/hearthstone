package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.objct.GameObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 从手牌中打出随从卡牌事件
 * @author Eiden J.P Zhou
 * @date 2019/10/29 14:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MinionDeathEvent extends AbstractEvent {
    private GameObject target;

    public MinionDeathEvent(Gamer owner, GameObject target) {
        super(owner);
        this.target = target;
    }
}

package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 随从死亡事件
 * @author Eiden J.P Zhou
 * @date 2019/10/29 14:26
 */
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
public class MinionDeathEvent extends AbstractEvent {
    private AbstractMinionObject deathMinion;

    public MinionDeathEvent(Gamer owner ,AbstractMinionObject deathMinion) {
        super(owner);
        this.deathMinion = deathMinion;
    }
}

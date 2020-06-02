package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Eiden J.P Zhou
 * @date 2020/3/30 16:45
 */
@Setter
@Getter
public class MinionBeHurtEvent extends AbstractEvent {
    private Minion minion;
    private long dmg;

    public MinionBeHurtEvent(Minion minion, long dmg) {
        super(minion.getOwner());
        this.minion = minion;
        this.dmg = dmg;
    }
}

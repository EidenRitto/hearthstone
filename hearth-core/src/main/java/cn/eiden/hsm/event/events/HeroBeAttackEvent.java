package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;
import lombok.Getter;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/27 16:25
 */
public class HeroBeAttackEvent extends AbstractEvent {
    @Getter
    private Minion attacker;

    public HeroBeAttackEvent(Gamer owner, Minion attacker) {
        super(owner);
        this.attacker = attacker;
    }
}

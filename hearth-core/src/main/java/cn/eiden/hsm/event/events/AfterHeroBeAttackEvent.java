package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;
import lombok.Getter;

/**
 * 英雄被攻击之后
 * @author Eiden J.P Zhou
 * @date 2020/6/3 16:22
 */
public class AfterHeroBeAttackEvent extends AbstractEvent {
    @Getter
    private Minion attacker;

    public AfterHeroBeAttackEvent(Gamer owner, Minion attacker) {
        super(owner);
        this.attacker = attacker;
    }
}

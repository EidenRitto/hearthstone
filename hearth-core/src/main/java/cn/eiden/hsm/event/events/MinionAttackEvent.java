package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 随从攻击事件
 * @author Eiden J.P Zhou
 * @date 2020/6/13 9:18
 */
public class MinionAttackEvent extends AbstractEvent {
    private Minion attackMinion;
    private Minion beAttackMinion;

    public MinionAttackEvent(Minion attackMinion, Minion beAttackMinion) {
        super(attackMinion.getOwner());
        this.attackMinion = attackMinion;
        this.beAttackMinion = beAttackMinion;
    }

    public Minion getAttackMinion() {
        return attackMinion;
    }

    public Minion getBeAttackMinion() {
        return beAttackMinion;
    }
}

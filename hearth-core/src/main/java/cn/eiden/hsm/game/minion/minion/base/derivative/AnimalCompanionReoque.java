package cn.eiden.hsm.game.minion.minion.base.derivative;

import cn.eiden.hsm.game.keyword.Aura;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.game.minion.Minion;

import java.util.List;

/**
 * 雷欧克
 *
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 */
public class AnimalCompanionReoque extends MinionObject implements Aura {
    private static final String SERVANT_NAME = "雷欧克";
    private static final Long ATTACK = 2L;
    private static final Long HEALTH = 4L;

    private static final long BUFF_ATTACK = 1;
    private static final long BUFF_HEALTH = 0;

    public AnimalCompanionReoque() {
        super(SERVANT_NAME, HEALTH, ATTACK);
    }

    @Override
    public void doAura() {
        List<Minion> minions = this.getOwner().getMinions();
        for (Minion minion : minions) {
            if (!minion.equals(this)) {
                minion.addAttack(BUFF_ATTACK);
            }
        }
    }
}

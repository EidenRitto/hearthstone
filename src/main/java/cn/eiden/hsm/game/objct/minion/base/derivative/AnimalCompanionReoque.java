package cn.eiden.hsm.game.objct.minion.base.derivative;

import cn.eiden.hsm.game.keyword.BuffHole;
import cn.eiden.hsm.game.tags.Ethnicity;
import cn.eiden.hsm.game.objct.MinionObject;
import cn.eiden.hsm.game.objct.Minion;

import java.util.List;

/**
 * 雷欧克
 *
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 */
public class AnimalCompanionReoque extends MinionObject implements BuffHole {
    private static final String SERVANT_NAME = "雷欧克";
    private static final Long ATTACK = 2L;
    private static final Long HEALTH = 4L;

    private static final long BUFF_ATTACK = 1;
    private static final long BUFF_HEALTH = 0;

    public AnimalCompanionReoque() {
        super(SERVANT_NAME, HEALTH, ATTACK);
        ethnicity = Ethnicity.Beast;
    }

    @Override
    public void doBuffHole() {
        List<Minion> minions = this.getOwner().getMinions();
        for (Minion minion : minions) {
            if (!minion.equals(this)) {
                minion.addAttack(BUFF_ATTACK);
            }
        }
    }
}

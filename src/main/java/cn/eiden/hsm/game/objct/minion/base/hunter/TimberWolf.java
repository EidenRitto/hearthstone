package cn.eiden.hsm.game.objct.minion.base.hunter;

import cn.eiden.hsm.game.keyword.BuffHole;
import cn.eiden.hsm.game.tags.Ethnicity;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.Minion;

import java.util.List;

/**
 * 森林狼
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *
 *
 */
public class TimberWolf extends AbstractMinionObject implements BuffHole {
    private static final String SERVANT_NAME = "森林狼";
    private static final Long ATTACK = 1L;
    private static final Long HEALTH = 1L;

    private static final long BUFF_ATTACK = 1;
    private static final long BUFF_HEALTH = 0;
    private static final Ethnicity BUFF_PREREQUISITE = Ethnicity.Beast;

    public TimberWolf() {
        super(SERVANT_NAME,HEALTH, ATTACK);
        ethnicity = Ethnicity.Beast;
    }


    @Override
    public void doBuffHole() {
        List<Minion> minions = this.getOwner().getMinions();
        for (Minion minion : minions) {
            if (minion.getEthnicity() == BUFF_PREREQUISITE && !minion.equals(this) ){
                minion.addAttack(BUFF_ATTACK);
            }
        }
    }
}

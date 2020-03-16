package cn.eiden.hsm.game.objct.minion.base.hunter;

import cn.eiden.hsm.game.keyword.BuffHole;
import cn.eiden.hsm.game.objct.Ethnicity;
import cn.eiden.hsm.game.objct.AbstractMinionObject;

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
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    private static final long BUFF_ATTACK = 1;
    private static final long BUFF_HEALTH = 0;
    private static final Ethnicity BUFF_PREREQUISITE = Ethnicity.Beast;

    public TimberWolf() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        ethnicity = Ethnicity.Beast;
    }


    @Override
    public void doBuffHole(AbstractMinionObject selfMinion) {
        List<AbstractMinionObject> minions = selfMinion.getOwner().getMinions();
        for (AbstractMinionObject minion : minions) {
            if (minion.getEthnicity() == BUFF_PREREQUISITE && !minion.equals(selfMinion) ){
                minion.addAttack(BUFF_ATTACK);
            }
        }
    }
}

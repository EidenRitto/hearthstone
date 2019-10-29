package cn.eiden.hsm.game.objct.minion.base.hunter;

import cn.eiden.hsm.game.objct.BuffHole;
import cn.eiden.hsm.game.objct.Ethnicity;
import cn.eiden.hsm.game.objct.AbstractMinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * @Description: 森林狼
 * @Modified By:
 */
public class TimberWolf extends AbstractMinionObject {
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
        buffHole = new BuffHole(BUFF_ATTACK,BUFF_HEALTH,BUFF_PREREQUISITE);
    }


}

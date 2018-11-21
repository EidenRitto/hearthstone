package game.objct.minion.base.derivative;

import game.objct.BuffHole;
import game.objct.Ethnicity;
import game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description:
 * @Modified By:
 */
public class AnimalCompanionReoque extends MinionObject {
    private static final String SERVANT_NAME = "雷欧克";
    private static final Long ATTACK = 2L;
    private static final Long HEALTH = 4L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    private static final long BUFF_ATTACK = 1;
    private static final long BUFF_HEALTH = 0;

    public AnimalCompanionReoque() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        ethnicity = Ethnicity.Beast;
        buffHole = new BuffHole(BUFF_ATTACK,BUFF_HEALTH,null);
    }
}

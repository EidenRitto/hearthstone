package cn.eiden.hsm.game.objct.minion.base.derivative;

import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.Ethnicity;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * @Description: 米莎
 * @Modified By:
 */
public class AnimalCompanionMisa extends AbstractMinionObject {
    private static final String SERVANT_NAME = "米莎";
    private static final Long ATTACK = 4L;
    private static final Long HEALTH = 4L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    public AnimalCompanionMisa() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        isTaunt = true;
        ethnicity = Ethnicity.Beast;
    }
}

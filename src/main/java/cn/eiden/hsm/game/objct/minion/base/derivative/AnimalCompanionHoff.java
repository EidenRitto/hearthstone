package cn.eiden.hsm.game.objct.minion.base.derivative;

import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.Ethnicity;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * @Description: 霍夫4/2 冲锋
 * @Modified By:
 */
public class AnimalCompanionHoff extends AbstractMinionObject {
    private static final String SERVANT_NAME = "霍夫";
    private static final Long ATTACK = 4L;
    private static final Long HEALTH = 2L;
    private static final boolean ATTACK_STATE = true;
    private static final int ATTACK_TIME = 1;

    public AnimalCompanionHoff() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        ethnicity = Ethnicity.Beast;
    }
}

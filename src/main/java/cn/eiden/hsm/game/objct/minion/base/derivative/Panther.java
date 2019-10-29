package cn.eiden.hsm.game.objct.minion.base.derivative;

import cn.eiden.hsm.game.objct.Ethnicity;
import cn.eiden.hsm.game.objct.AbstractMinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/20
 * @Description:
 * @Modified By: 野性之力衍生物 黑豹
 */
public class Panther extends AbstractMinionObject {
    private static final String SERVANT_NAME = "黑豹";
    private static final Long ATTACK = 3L;
    private static final Long HEALTH = 2L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    public Panther() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        ethnicity = Ethnicity.Beast;
    }
}

package cn.eiden.hsm.game.objct.minion.base.derivative;

import cn.eiden.hsm.game.keyword.Charge;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.Ethnicity;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  霍夫4/2 冲锋
 */
public class AnimalCompanionHoff extends AbstractMinionObject implements Charge {
    private static final String SERVANT_NAME = "霍夫";
    private static final Long ATTACK = 4L;
    private static final Long HEALTH = 2L;

    public AnimalCompanionHoff() {
        super(SERVANT_NAME,HEALTH, ATTACK);
        ethnicity = Ethnicity.Beast;
    }
}

package cn.eiden.hsm.game.objct.minion.base.derivative;

import cn.eiden.hsm.game.keyword.Taunt;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.Ethnicity;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  米莎
 *
 */
public class AnimalCompanionMisa extends AbstractMinionObject implements Taunt {
    private static final String SERVANT_NAME = "米莎";
    private static final Long ATTACK = 4L;
    private static final Long HEALTH = 4L;

    public AnimalCompanionMisa() {
        super(SERVANT_NAME,HEALTH, ATTACK);
        ethnicity = Ethnicity.Beast;
    }
}

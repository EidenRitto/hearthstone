package cn.eiden.hsm.game.objct.minion.base.derivative;

import cn.eiden.hsm.game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/20
 *  野性之力衍生物 黑豹
 *
 */
public class Panther extends MinionObject {
    private static final String SERVANT_NAME = "黑豹";
    private static final Long ATTACK = 3L;
    private static final Long HEALTH = 2L;

    public Panther() {
        super(SERVANT_NAME,HEALTH, ATTACK);
    }
}

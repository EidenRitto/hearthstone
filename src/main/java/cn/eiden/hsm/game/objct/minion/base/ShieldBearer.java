package cn.eiden.hsm.game.objct.minion.base;

import cn.eiden.hsm.game.objct.AbstractMinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/21
 *  持盾卫士
 *
 */
public class ShieldBearer extends AbstractMinionObject {
    private static final String SERVANT_NAME = "持盾卫士";
    private static final Long HEALTH = 4L;
    private static final Long ATTACK = 0L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    public ShieldBearer() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        isTaunt = true;
    }
}

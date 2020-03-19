package cn.eiden.hsm.game.objct.minion.base;

import cn.eiden.hsm.game.keyword.Taunt;
import cn.eiden.hsm.game.objct.AbstractMinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/21
 *  持盾卫士
 *
 */
public class ShieldBearer extends AbstractMinionObject implements Taunt {
    private static final String SERVANT_NAME = "持盾卫士";
    private static final Long HEALTH = 4L;
    private static final Long ATTACK = 0L;

    public ShieldBearer() {
        super(SERVANT_NAME,HEALTH, ATTACK);
    }
}

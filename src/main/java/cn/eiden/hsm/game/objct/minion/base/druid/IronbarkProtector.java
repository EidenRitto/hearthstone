package cn.eiden.hsm.game.objct.minion.base.druid;

import cn.eiden.hsm.game.keyword.Taunt;
import cn.eiden.hsm.game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  埃隆巴克保护者
 *
 */
public class IronbarkProtector extends MinionObject implements Taunt {
    private static final String SERVANT_NAME = "埃隆巴克保护者";
    private static final Long HEALTH = 8L;
    private static final Long ATTACK = 8L;

    public IronbarkProtector() {
        super(SERVANT_NAME,HEALTH, ATTACK);
    }
}

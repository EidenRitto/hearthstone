package game.objct.base.druid;

import game.objct.MinionObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description: 埃隆巴克保护者
 * @Modified By:
 */
public class IronbarkProtector extends MinionObject {
    private static final String SERVANT_NAME = "埃隆巴克保护者";
    private static final Long HEALTH = 8L;
    private static final Long ATTACK = 8L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    public IronbarkProtector() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        isTaunt=true;
    }
}

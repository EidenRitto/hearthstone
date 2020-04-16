package cn.eiden.hsm.game.objct;

import cn.eiden.hsm.enums.Race;

/**
 * 随从（真）类
 * @author Eiden J.P Zhou
 * @date 2020/4/4 15:44
 */
public class MinionObject extends AbstractMinion {
    public MinionObject() {
    }

    public MinionObject(String minionName, Long healthLimit, Long attackValue) {
        this(minionName, healthLimit, attackValue,Race.INVALID);
    }

    public MinionObject(String minionName, Long healthLimit, Long attackValue, Race race) {
        super(minionName, healthLimit, attackValue,race);
    }
}

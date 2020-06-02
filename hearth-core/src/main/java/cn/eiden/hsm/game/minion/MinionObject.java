package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.enums.Race;

/**
 * 随从（真）类
 * @author Eiden J.P Zhou
 * @date 2020/4/4 15:44
 */
public class MinionObject extends AbstractMinion {

    public MinionObject() {
    }

    public MinionObject(String minionName, Long healthLimit, Long attackValue, Race race, String cardId) {
        super(minionName, healthLimit, attackValue,race,cardId);
    }

}

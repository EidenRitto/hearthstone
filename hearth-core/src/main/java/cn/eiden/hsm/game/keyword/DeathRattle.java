package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.game.minion.Minion;

/**
 * 亡语
 * @author Eiden J.P Zhou
 * @date 2019/10/29 10:30
 */
public interface DeathRattle {
    /**
     * 执行亡语
     * @param minion 当前随从
     */
    void doDeathRattle(Minion minion);
}

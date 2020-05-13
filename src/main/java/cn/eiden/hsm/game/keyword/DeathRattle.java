package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.game.objct.Minion;

/**
 * 亡语
 * @author Eiden J.P Zhou
 * @date 2019/10/29 10:30
 */
public interface DeathRattle {
    /**
     * 设置当前随从
     * @param minion 随从
     */
    void setCurrentMinion(Minion minion);
    /**
     * 执行亡语
     */
    void doDeathRattle();
}

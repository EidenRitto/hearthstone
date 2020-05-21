package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.game.minion.Minion;

/**
 * 战吼接口
 * @author Eiden J.P Zhou
 * @date 2019/10/29 10:10
 */
public interface Battle {
    /**
     * 执行战吼
     * @param self 当前随从
     * @param target 所选目标
     */
    void doBattle(Minion self, Minion target);
}

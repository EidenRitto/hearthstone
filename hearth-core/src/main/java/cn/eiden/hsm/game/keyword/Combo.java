package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.game.minion.Minion;

/**
 * 连击
 * @author Eiden J.P Zhou
 * @date 2020/6/7 14:00
 */
@FunctionalInterface
public interface Combo {
    /**
     * 执行连击
     * @param self 当前随从
     * @param target 所选目标
     */
    void doCombo(Minion self, Minion target);
}

package cn.eiden.hsm.game.keyword;


import cn.eiden.hsm.game.minion.Minion;

/**
 * 光环
 * @author Eiden J.P Zhou
 * @date 2020/3/16 17:22
 */
public interface Aura {
    /**
     * 效果
     * @param minion 当前光环随从
     */
    void doAura(Minion minion);
}

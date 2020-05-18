package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/18 14:29
 */
public interface SpellEffect {

    /**
     * 魔法效果
     * @param gamer 当前玩家
     * @param target 目标单位
     */
    void magicEffect(Gamer gamer, Minion target);
}

package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/11 11:42
 */
public interface Outcast {
    /**
     * 流放效果
     * @param gamer 当前卡牌所有者
     * @param target 所指定目标
     */
    void outcastEffect(Gamer gamer, Minion target);
}

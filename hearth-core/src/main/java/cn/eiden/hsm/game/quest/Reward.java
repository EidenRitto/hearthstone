package cn.eiden.hsm.game.quest;

import cn.eiden.hsm.game.Gamer;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/9 23:16
 */
@FunctionalInterface
public interface Reward {
    void earnRewards(Gamer gamer);
}

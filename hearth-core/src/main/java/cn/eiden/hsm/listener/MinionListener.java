package cn.eiden.hsm.listener;

import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 9:45
 */
public interface MinionListener extends HearthListener{
    /**
     * 随从监听所绑定的随从
     * @return 随从它本身
     */
    Minion getMinion();

    /**
     * 绑定随从
     * @param minion 随从
     */
    void setMinion(Minion minion);

    /**
     * 克隆
     * @return 返回一个副本,需要手动修改随从引用
     */
    MinionListener clone();
}

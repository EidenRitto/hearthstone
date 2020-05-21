package cn.eiden.hsm.game;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.keyword.DeathRattle;

/**
 * 一般组件
 *
 * @author Eiden J.P Zhou
 * @date 2020/5/21 10:18
 */
public interface GeneralItem {
    /**
     * 获取拥有者
     *
     * @return 拥有者
     */
    Gamer getOwner();

    /**
     * 设置拥有者
     *
     * @param owner 拥有者
     */
    void setOwner(Gamer owner);

    /**
     * 添加亡语
     *
     * @param deathRattle 亡语
     */
    void addDeathRattle(DeathRattle deathRattle);

    /**
     * 移除亡语
     */
    void removeDeathRattle();

    /**
     * 是否有亡语
     *
     * @return 有亡语返回true
     */
    boolean hasDeathRattle();

    /**
     * 执行亡语
     */
    void doDeathRattle();
}

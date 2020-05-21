package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.game.GeneralItem;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/20 17:08
 */
public interface Weapon extends GeneralItem {
    /**销毁*/
    void destroy();

    /**耐久消耗*/
    void durableConsumed();
}

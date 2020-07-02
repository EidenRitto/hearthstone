package cn.eiden.hsm.listener;

import cn.eiden.hsm.game.minion.Weapon;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 10:49
 */
public interface WeaponListener extends HearthListener {
    /**
     * 获取武器
     * @return 武器
     */
    Weapon getWeapon();

    /**
     * 设置武器
     * @param weapon 武器
     */
    void setWeapon(Weapon weapon);

    /**
     * 克隆
     * @return 返回一个副本,需要手动修改随从引用
     */
    WeaponListener clone();
}

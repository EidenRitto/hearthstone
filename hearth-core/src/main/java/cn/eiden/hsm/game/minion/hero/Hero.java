package cn.eiden.hsm.game.minion.hero;

import cn.eiden.hsm.game.minion.Weapon;

/**
 * 英雄
 * @author Eiden J.P Zhou
 * @date 2020/5/20 17:43
 */
public interface Hero {
    /**
     * 叠甲
     * @param armorNumber 护甲值
     */
    void addArmor(long armorNumber);
    /**
     * 装备武器
     * @param weapon 武器
     */
    void equipWeapons(Weapon weapon);
}

package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.game.GeneralItem;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/20 17:08
 */
public interface Weapon extends GeneralItem {
    /**
     * 销毁
     */
    void destroy();

    /**
     * 耐久消耗
     */
    void durableConsumed();

    /**
     * 是否还有耐久度，检查耐久是否大于0
     *
     * @return 大于0返回true
     */
    boolean hasDurable();

    /**
     * 获取武器攻击力的值
     *
     * @return 武器攻击力的值
     */
    long getWeaponAttack();

    /**
     * 增加攻击力
     *
     * @param atkValue 攻击力
     */
    void addAttackValue(long atkValue);

    /**
     * 增加耐久度
     *
     * @param durableValue 耐久度
     */
    void addDurable(long durableValue);
}

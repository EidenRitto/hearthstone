package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.listener.WeaponListener;

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

    /**
     * 获取名字
     * @return 名字
     */
    String getName();

    /**
     * 是否打开
     * @return 打开返回true
     */
    boolean isOpen();

    /**
     * 获取自带监听
     * @return 武器监听
     */
    WeaponListener getWeaponListener();

    /**
     * 设置武器监听
     * @param weaponListener 武器监听
     */
    void setWeaponListener(WeaponListener weaponListener);

}

package cn.eiden.hsm.listener;

import cn.eiden.hsm.game.minion.Weapon;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 10:50
 */
public abstract class AbstractWeaponListener implements WeaponListener{
    /**监听所绑定的武器*/
    private Weapon weapon;

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public AbstractWeaponListener(Weapon weapon) {
        this.weapon = weapon;
    }

    public AbstractWeaponListener() {
    }
}

package game.objct.weapon;

import game.objct.GameObject;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/26
 * @Description: 武器对象
 * @Modified By:
 */
public class WeaponObject extends GameObject{
    private String weaponName;
    private long weaponAttack;
    private long weaponDurable;

    public WeaponObject() {
    }

    public WeaponObject(String weaponName, long weaponAttack, long weaponDurable) {
        this.weaponName = weaponName;
        this.weaponAttack = weaponAttack;
        this.weaponDurable = weaponDurable;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public long getWeaponAttack() {
        return weaponAttack;
    }

    public void setWeaponAttack(long weaponAttack) {
        this.weaponAttack = weaponAttack;
    }

    public long getWeaponDurable() {
        return weaponDurable;
    }

    public void setWeaponDurable(long weaponDurable) {
        this.weaponDurable = weaponDurable;
    }
}

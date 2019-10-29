package cn.eiden.hsm.game.card;


import cn.eiden.hsm.game.objct.WeaponObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 * @Description:
 * @Modified By:
 */
public class WeaponCard extends AbstractCard {
    /**武器攻击力*/
    private long weaponAttack;
    /**耐久度*/
    private long weaponDurable;
    /**卡牌对应的随从*/
    private WeaponObject weapon;

    @Override
    public void useCard() {

    }

    public WeaponCard(int cost, String description,String cardName,long weaponAttack,long weaponDurable,WeaponObject weapon) {
        super(cost, description, cardName);
        this.weaponAttack = weaponAttack;
        this.weaponDurable = weaponDurable;
        this.weapon = weapon;
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

    public WeaponObject getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponObject weapon) {
        this.weapon = weapon;
    }
}

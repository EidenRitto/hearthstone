package cn.eiden.hsm.game.card;


import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.objct.WeaponObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 */
@Setter
@Getter
public abstract class AbstractWeaponCard extends AbstractCard {
    /**武器攻击力*/
    private long weaponAttack;
    /**耐久度*/
    private long weaponDurable;
    /**卡牌对应的随从*/
    private WeaponObject weapon;

    public AbstractWeaponCard(int cost, String description, String cardName, Profession profession, long weaponAttack, long weaponDurable, WeaponObject weapon) {
        super(cost, description, cardName, profession);
        this.weaponAttack = weaponAttack;
        this.weaponDurable = weaponDurable;
        this.weapon = weapon;
    }
}

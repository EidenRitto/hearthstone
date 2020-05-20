package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/26
 */
@Setter
@Getter
public class WeaponObject extends GameObject implements Weapon{
    private String weaponName;
    private long weaponAttack;
    private long weaponDurable;

    public void whenAttackDo(Gamer gamer){

    }

    public WeaponObject(String weaponName, long weaponAttack, long weaponDurable) {
        this.weaponName = weaponName;
        this.weaponAttack = weaponAttack;
        this.weaponDurable = weaponDurable;
    }
}

package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.event.events.BattlefieldChangeEvent;
import cn.eiden.hsm.event.events.WeaponDestroyEvent;
import cn.eiden.hsm.game.AbstractGeneralItem;
import cn.eiden.hsm.game.Gamer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/26
 */
@Setter
@Getter
public class WeaponObject extends AbstractGeneralItem implements Weapon{
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

    @Override
    public void destroy() {
        WeaponDestroyEvent destroyEvent = new WeaponDestroyEvent(this);
        getOwner().getEventManager().call(destroyEvent);
        getOwner().getEventManager().call(new BattlefieldChangeEvent(getOwner()));
    }

    @Override
    public void durableConsumed() {
        weaponDurable = weaponDurable - 1L;
        getOwner().getEventManager().call(new BattlefieldChangeEvent(getOwner()));
    }

    @Override
    public boolean hasDurable() {
        return weaponDurable > 0;
    }

    @Override
    public long getWeaponAttack() {
        return weaponAttack;
    }

    @Override
    public void addAttackValue(long atkValue) {
        weaponAttack += atkValue;
    }

    @Override
    public void addDurable(long durableValue) {
        weaponDurable += durableValue;
    }

    @Override
    public String getName() {
        return this.weaponName;
    }
}

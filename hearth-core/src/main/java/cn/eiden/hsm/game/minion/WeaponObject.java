package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.event.events.BattlefieldChangeEvent;
import cn.eiden.hsm.event.events.WeaponDestroyEvent;
import cn.eiden.hsm.game.AbstractGeneralItem;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.listener.WeaponListener;
import cn.eiden.hsm.output.OutputInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/26
 */
@Setter
@Getter
public class WeaponObject extends AbstractGeneralItem implements Weapon {
    private String weaponName;
    private long weaponAttack;
    private long weaponDurable;
    /**
     * 战吼
     */
    private Battle battle;
    /**
     * 自带监听
     */
    private WeaponListener weaponListener;

    public void whenAttackDo(Gamer gamer) {

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
        return isOpen() ? weaponAttack : 0;
    }

    @Override
    public void addAttackValue(long atkValue) {
        getOwner().printPublicQueue(String.format("%s获得%s点攻击力", weaponName, atkValue));
        weaponAttack += atkValue;
    }

    @Override
    public void addDurable(long durableValue) {
        getOwner().printPublicQueue(String.format("%s获得%s点耐久", weaponName, durableValue));
        weaponDurable += durableValue;
    }

    @Override
    public String getName() {
        return this.weaponName;
    }

    @Override
    public boolean isOpen() {
        return getOwner().isActive();
    }

    @Override
    public WeaponListener getWeaponListener() {
        return weaponListener;
    }

    @Override
    public void setWeaponListener(WeaponListener weaponListener) {
        this.weaponListener = weaponListener;
        weaponListener.setWeapon(this);
    }

    @Override
    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    @Override
    public void doBattle(Minion target) {
        this.battle.doBattle(getOwner().getHero(), target);
    }

    @Override
    public boolean hasBattle() {
        return this.battle != null;
    }
}

package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.event.EventManager;
import cn.eiden.hsm.event.events.WeaponDestroyEvent;
import cn.eiden.hsm.game.AbstractGeneralItem;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.keyword.DeathRattle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * 事件管理器
     */
    private final EventManager eventManager = EventManager.getInstance();

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
        eventManager.call(destroyEvent);
    }

    @Override
    public void durableConsumed() {
        weaponDurable = weaponDurable - 1L;
    }

    @Override
    public boolean hasDurable() {
        return weaponDurable > 0;
    }

    @Override
    public long getWeaponAttack() {
        return weaponAttack;
    }
}

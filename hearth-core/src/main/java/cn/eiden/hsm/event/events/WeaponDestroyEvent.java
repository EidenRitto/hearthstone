package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.minion.Weapon;
import lombok.Getter;

/**
 * 武器销毁事件
 * @author Eiden J.P Zhou
 * @date 2020/5/21 9:32
 */
public class WeaponDestroyEvent extends AbstractEvent {
    @Getter
    private Weapon weapon;

    public WeaponDestroyEvent(Weapon weapon) {
        super(weapon.getOwner());
        this.weapon = weapon;
    }
}

package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.WeaponDestroyEvent;
import cn.eiden.hsm.game.minion.Weapon;
import cn.eiden.hsm.listener.HearthListener;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/21 9:51
 */
public class WeaponDestroyListener implements HearthListener {
    @EventHandler
    public void onEvent(WeaponDestroyEvent event){
        Weapon weapon = event.getWeapon();
        if (weapon.hasDeathRattle()){
            weapon.doDeathRattle();
        }
    }
}

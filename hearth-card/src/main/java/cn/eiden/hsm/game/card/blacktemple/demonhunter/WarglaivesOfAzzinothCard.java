package cn.eiden.hsm.game.card.blacktemple.demonhunter;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.MinionAttackEvent;
import cn.eiden.hsm.game.card.defs.black_temple.demonhunter.WarglaivesOfAzzinoth;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.game.minion.Weapon;
import cn.eiden.hsm.game.minion.hero.HeroMinion;
import cn.eiden.hsm.listener.AbstractWeaponListener;

/**
 * 埃辛诺斯战刃
 * @author Eiden J.P Zhou
 * @date 2020/6/13 15:43
 */
public class WarglaivesOfAzzinothCard extends WarglaivesOfAzzinoth {
    @Override
    public Weapon createWeapon() {
        Weapon weapon = super.createWeapon();
        weapon.setWeaponListener(new Listener());
        return weapon;
    }

    private static class Listener extends AbstractWeaponListener{
        @EventHandler
        public void onEvent(MinionAttackEvent event){
            Minion minion = event.getAttackMinion();
            Minion beAttackMinion = event.getBeAttackMinion();
            if (minion instanceof HeroMinion){
                if (((HeroMinion) minion).getWeapon() == getWeapon()
                        && beAttackMinion instanceof MinionObject){
                    minion.addAttackTime(1);
                }
            }
        }
    }
}

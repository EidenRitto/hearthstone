package cn.eiden.hsm.game.card.lootapalooza.mage;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.EndTurnEvent;
import cn.eiden.hsm.game.card.defs.lootapalooza.mage.Aluneth;
import cn.eiden.hsm.game.minion.Weapon;
import cn.eiden.hsm.listener.AbstractWeaponListener;
import cn.eiden.hsm.listener.WeaponListener;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 10:55
 */
public class AlunethCard extends Aluneth {
    @Override
    public Weapon createWeapon() {
        Weapon weapon = super.createWeapon();
        weapon.setWeaponListener(new EffectListener(weapon));
        return weapon;
    }

    static class EffectListener extends AbstractWeaponListener implements WeaponListener{
        private static final int DRAW_CARD_NUM = 3;
        @EventHandler
        public void onEvent(EndTurnEvent event){
            if (event.getOwner() == getWeapon().getOwner()){
                event.getOwner().drawCard(DRAW_CARD_NUM);
            }
        }

        public EffectListener(Weapon weapon) {
            super(weapon);
        }
    }
}

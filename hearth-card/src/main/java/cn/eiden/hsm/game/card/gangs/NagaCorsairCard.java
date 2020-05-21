package cn.eiden.hsm.game.card.gangs;

import cn.eiden.hsm.game.card.defs.gangs.neutral.NagaCorsair;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Weapon;
import cn.eiden.hsm.game.minion.hero.Hero;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/21 17:33
 */
public class NagaCorsairCard extends NagaCorsair {
    @Override
    protected Battle selfBattleCry() {
        return new BattleCry();
    }

    static public class BattleCry implements Battle {
        @Override
        public void doBattle(Minion self, Minion target) {
            Hero hero = self.getOwner().getHero();
            if (hero.hasWeapon()){
                Weapon weapon = hero.getWeapon();
                weapon.addAttackValue(1L);
            }
        }
    }
}

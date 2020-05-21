package cn.eiden.hsm.game.card.classic;

import cn.eiden.hsm.game.card.defs.expert1.neutral.BloodsailRaider;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.hero.Hero;

/**
 * @author EidenRitto
 * @date 2020/5/21 17:14
 */
public class BloodsailRaiderCard extends BloodsailRaider {
    @Override
    protected Battle selfBattleCry() {
        return new BattleCry();
    }

    public static class BattleCry implements Battle{

        @Override
        public void doBattle(Minion self, Minion target) {
            Hero hero = self.getOwner().getHero();
            if (hero.hasWeapon()){
                self.addAttack(hero.getWeapon().getWeaponAttack());
            }
        }
    }
}

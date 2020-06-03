package cn.eiden.hsm.game.card.og.warrior;

import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.card.defs.og.warrior.BloodsailCultist;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Weapon;
import cn.eiden.hsm.game.minion.hero.Hero;

import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/21 17:28
 */
public class BloodsailCultistCard extends BloodsailCultist {
    @Override
    protected Battle selfBattleCry() {
        return new BattleCry();
    }
    public static class BattleCry implements Battle{
        @Override
        public void doBattle(Minion self, Minion target) {
            Hero hero = self.getOwner().getHero();
            List<Minion> minions = self.getOwner().getMinions();
            for (Minion minion : minions) {
                if (minion.getRace() == Race.PIRATE && minion != self){
                    if (hero.hasWeapon()){
                        Weapon weapon = hero.getWeapon();
                        weapon.addAttackValue(1L);
                        weapon.addDurable(1L);
                    }
                    break;
                }
            }
        }
    }
}

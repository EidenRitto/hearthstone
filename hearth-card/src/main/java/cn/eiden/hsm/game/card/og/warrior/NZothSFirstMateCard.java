package cn.eiden.hsm.game.card.og.warrior;

import cn.eiden.hsm.game.card.AbstractWeaponCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.og.warrior.NZothSFirstMate;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.hero.Hero;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/21 17:21
 */
public class NZothSFirstMateCard extends NZothSFirstMate {
    /**关联id*/
    private static final int REF_ID = 38363;
    @Override
    protected Battle selfBattleCry() {
        return new BattleCry();
    }

    public static class BattleCry implements Battle{

        @Override
        public void doBattle(Minion self, Minion target) {
            AbstractWeaponCard weaponCard = (AbstractWeaponCard) CardFactory.getCardById(REF_ID);
            Hero hero = self.getOwner().getHero();
            assert weaponCard != null;
            hero.equipWeapons(weaponCard.createWeapon());
        }
    }
}

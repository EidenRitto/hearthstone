package cn.eiden.hsm.game.card.dalaran.mage;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.dalaran.mage.RayOfFrost_1;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/11 10:12
 */
public class RayOfFrostCopyCard extends RayOfFrost_1 {
    private static long DMG = 2;

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        if (target.isFreeze()) {
            //对其造成$2点伤害
            target.beHurt(DMG + gamer.getGamerSpellDamage());
            target.getOwner().checkMinion();
        } else {
            //冻结一个随从
            target.freeze();
        }
    }
}

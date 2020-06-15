package cn.eiden.hsm.game.card.dalaran.druid;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.dalaran.druid.PiercingThorns;
import cn.eiden.hsm.game.keyword.SpellDamage;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;

/**
 * 利刺荆棘
 * @author Eiden J.P Zhou
 * @date 2020/6/15 17:55
 */
@TargetScope(classScope = MinionObject.class)
public class PiercingThornsCard extends PiercingThorns implements SpellDamage {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        target.beHurt(this.getDamage());
    }

    @Override
    public int getDamage() {
        return getOwner().getGamerSpellDamage() + 2;
    }
}

package cn.eiden.hsm.game.card.blacktemple.druid;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.black_temple.druid.Bogbeam;
import cn.eiden.hsm.game.keyword.SpellDamage;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;

/**
 * 沼泽射线
 * @author Eiden J.P Zhou
 * @date 2020/6/17 8:42
 */
@TargetScope(classScope = MinionObject.class)
public class BogbeamCard extends Bogbeam implements SpellDamage {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        target.beHurt(this.getDamage());
    }

    @Override
    public int getCost() {
        return getOwner().getManaCrystal().getManaCrystal() > 7 ? 0 : super.getCost();
    }

    @Override
    public int getDamage() {
        return getOwner().getGamerSpellDamage() + 3;
    }
}

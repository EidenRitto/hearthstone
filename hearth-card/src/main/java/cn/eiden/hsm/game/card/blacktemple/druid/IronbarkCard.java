package cn.eiden.hsm.game.card.blacktemple.druid;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.black_temple.druid.Ironbark;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;

/**
 * 铁木树皮
 * @author Eiden J.P Zhou
 * @date 2020/6/17 8:39
 */
@TargetScope(classScope = MinionObject.class)
public class IronbarkCard extends Ironbark {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        target.addHealthLimit(3);
        target.addAttack(1);
        target.addTaunt();
    }

    @Override
    public int getCost() {
        return getOwner().getManaCrystal().getManaCrystal() > 7 ? 0 : super.getCost();
    }
}

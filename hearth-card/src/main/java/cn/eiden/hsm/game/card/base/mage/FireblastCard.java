package cn.eiden.hsm.game.card.base.mage;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.mage.Fireblast;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 16:58
 */
@TargetScope
public class FireblastCard extends Fireblast {
    private static final int DMG = 1;

    @Override
    public void powerEffect(Gamer gamer, Minion target) {
        target.beHurt(gamer.getHero(),DMG);
    }
}

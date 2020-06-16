package cn.eiden.hsm.game.card.dalaran.druid;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.dalaran.druid.HealingBlossom;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 愈合之花
 * @author Eiden J.P Zhou
 * @date 2020/6/15 18:00
 */
@TargetScope
public class HealingBlossomCard extends HealingBlossom {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        target.recoveryHp(5);
    }
}

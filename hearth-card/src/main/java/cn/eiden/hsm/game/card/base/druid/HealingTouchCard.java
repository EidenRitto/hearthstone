package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.card.defs.core.druid.HealingTouch;
import cn.eiden.hsm.game.minion.Minion;

import cn.eiden.hsm.game.Gamer;


/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *  生命之触
 *
 */
@TargetScope
public class HealingTouchCard extends HealingTouch {
    private static final long HEALTH_NUMBER = 8;

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        target.recoveryHp(HEALTH_NUMBER);
    }
}

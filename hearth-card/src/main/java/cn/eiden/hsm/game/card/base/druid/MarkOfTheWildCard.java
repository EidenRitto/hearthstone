package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.druid.MarkOfTheWild;

import cn.eiden.hsm.game.minion.Minion;

import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  野性印记
 *
 */
@TargetScope(classScope = MinionObject.class)
public class MarkOfTheWildCard extends MarkOfTheWild {
    /**增加的生命值*/
    private static final long ADD_HEALTH = 2;
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        target.addAttack(ADD_ATTACK);
        target.addHealthLimit(ADD_HEALTH);
        target.addTaunt();
        OutputInfo.info(target.getMinionName()+"获得嘲讽.");
    }
}

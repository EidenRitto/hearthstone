package cn.eiden.hsm.game.card.uldum.neutral;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.card.defs.uldum.neutral.BeamingSidekick;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.game.tags.Stand;

/**
 * 欢快的同伴
 * @author Eiden J.P Zhou
 * @date 2020/6/13 14:28
 */
@TargetScope(classScope = MinionObject.class ,stand = Stand.FRIEND)
public class BeamingSidekickCard extends BeamingSidekick {
    @Override
    protected Battle selfBattleCry() {
        return (s,t) -> t.addHealthLimit(2);
    }
}

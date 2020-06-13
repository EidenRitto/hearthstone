package cn.eiden.hsm.game.card.base.demonhunter;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.card.defs.core.demonhunter.GlaiveboundAdept;
import cn.eiden.hsm.game.keyword.Battle;

/**
 * 刃缚精锐
 * @author Eiden J.P Zhou
 * @date 2020/6/13 9:27
 */
@TargetScope
public class GlaiveboundAdeptCard extends GlaiveboundAdept {
    @Override
    protected Battle selfBattleCry() {
        return (s,t) -> t.beHurt(4);
    }

    @Override
    public boolean isNoneTarget() {
        return !getOwner().getHero().isAtkThisTurn();
    }
}

package cn.eiden.hsm.game.card.classic.rogue;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.card.defs.expert1.rogue.Si7Agent;
import cn.eiden.hsm.game.keyword.Combo;

/**
 * 军情七处特工
 * @author Eiden J.P Zhou
 * @date 2020/6/7 13:50
 */
@TargetScope
public class Si7AgentCard extends Si7Agent {
    public static final int DMG = 2;

    /**
     * "<b>连击：</b>造成2点伤害。"
     */
    @Override
    protected Combo selfCombo() {
        return (s,t) -> t.beHurt(s,DMG);
    }

    /**
     * 是否不需要目标
     *
     * @return 不需要目标返回true
     */
    @Override
    public boolean isNoneTarget() {
        return !this.getOwner().isComboTrigger();
    }
}

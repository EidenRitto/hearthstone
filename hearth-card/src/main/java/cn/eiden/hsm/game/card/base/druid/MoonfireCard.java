package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.druid.Moonfire;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/6 17:12
 */
@TargetScope
public class MoonfireCard extends Moonfire {
    private static final int DMG = 1;

    /**
     * "造成$1点伤害。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int damage = gamer.getGamerSpellDamage() + DMG;
        target.beHurt(damage);
    }
}

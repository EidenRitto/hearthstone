package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.druid.Starfire;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 星火术
 * @author Eiden J.P Zhou
 * @date 2020/6/6 17:32
 */
@TargetScope
public class StarfireCard extends Starfire {
    public static final int DMG = 5;

    /**
     * "造成$5点伤害。抽一张牌。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int damage = gamer.getGamerSpellDamage() + DMG;
        target.beHurt(damage);
        gamer.drawCard(1);
    }
}

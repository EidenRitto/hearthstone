package cn.eiden.hsm.game.card.base.mage;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.mage.Fireball;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 火球术
 * @author Eiden J.P Zhou
 * @date 2020/6/4 10:41
 */
@TargetScope
public class FireballCard extends Fireball {
    private static final int DMG = 6;
    /**
     * "造成$6点伤害。"
     * @param gamer 当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int damage = DMG + gamer.getGamerSpellDamage();
        target.beHurt(damage);
    }
}

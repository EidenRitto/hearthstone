package cn.eiden.hsm.game.card.loe.mage;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.loe.mage.RoaringTorch;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 炽烈的火把
 * @author Eiden J.P Zhou
 * @date 2020/6/3 19:42
 */
@TargetScope
public class RoaringTorchCard extends RoaringTorch {
    private static final int DMG = 6;
    /**
     * "造成$6点伤害。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int damage = DMG + gamer.getGamerSpellDamage();
        target.beHurt(damage);
    }
}

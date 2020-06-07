package cn.eiden.hsm.game.card.classic.rogue;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.expert1.rogue.Eviscerate;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 刺骨
 *
 * @author Eiden J.P Zhou
 * @date 2020/6/7 15:10
 */
@TargetScope
public class EviscerateCard extends Eviscerate {
    public static final int BASE_DMG = 2;
    public static final int COMBO_DMG = 4;

    /**
     * "造成$2点伤害；<b>连击：</b>改为造成$4点伤害。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int spellDamage = gamer.getGamerSpellDamage();
        int baseDamage = spellDamage + BASE_DMG;
        int comboDamage = spellDamage + COMBO_DMG;
        if (gamer.isComboTrigger()) {
            target.beHurt(comboDamage);
        } else {
            target.beHurt(baseDamage);
        }
    }
}

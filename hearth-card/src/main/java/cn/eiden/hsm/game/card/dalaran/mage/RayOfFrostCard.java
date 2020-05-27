package cn.eiden.hsm.game.card.dalaran.mage;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.dalaran.mage.RayOfFrost;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;

/**
 * 霜冻射线
 *
 * @author Eiden J.P Zhou
 * @date 2020/5/26 22:36
 */
@TargetScope(classScope = MinionObject.class)
public class RayOfFrostCard extends RayOfFrost {
    private static long demage = 2;

    /**
     * "<b>冻结</b>一个随从。如果该随从已被<b>冻结</b>，则对其造成$2点伤害。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        if (target.isFreeze()) {
            //对其造成$2点伤害
            target.beHurt(demage + gamer.getGamerSpellDamage());
        } else {
            //冻结一个随从
            target.freeze();
        }
    }
}

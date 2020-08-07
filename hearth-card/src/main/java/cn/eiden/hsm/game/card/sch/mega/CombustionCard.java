package cn.eiden.hsm.game.card.sch.mega;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.scholomance.mage.Combustion;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;

import java.util.Optional;

/**
 * 燃烧
 *
 * @author Eiden J.P Zhou
 * @date 2020/8/7 15:42
 */
@TargetScope(classScope = MinionObject.class)
public class CombustionCard extends Combustion {
    /**
     * "对一个随从造成$4点伤害，相邻的随从均会受到超过其生命值的伤害。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int damage = gamer.getGamerSpellDamage() + 4;
        //大于其生命值的伤害
        long overflowDamage = target.getHealth() - damage;
        target.beHurt(gamer.getHero(), damage);

        if (overflowDamage > 0) {
            int indexOfTarget = gamer.getMinions().indexOf(target);
            int lastIndex = indexOfTarget - 1;
            int nextIndex = indexOfTarget + 1;
            if (lastIndex >= 0) {
                Optional.ofNullable(gamer.getMinion(lastIndex))
                        .ifPresent(e -> e.beHurt(gamer.getHero(), overflowDamage));
            }
            if (nextIndex < Gamer.MAX_MINION_SIZE) {
                Optional.ofNullable(gamer.getMinion(nextIndex))
                        .ifPresent(e -> e.beHurt(gamer.getHero(), overflowDamage));
            }
        }
        //死亡结算
        gamer.checkMinion();
    }
}

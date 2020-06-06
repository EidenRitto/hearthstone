package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.druid.Swipe;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.tags.Stand;

import java.util.List;

/**
 * 横扫
 * @author Eiden J.P Zhou
 * @date 2020/6/6 17:34
 */
@TargetScope(stand = Stand.FOE)
public class SwipeCard extends Swipe {
    public static final int DMG_1 = 1;
    public static final int DMG_2 = 4;

    /**
     * "对一个敌人造成$4点伤害，并对所有其他敌人\n"
     * + "造成$1点伤害。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        List<Minion> allMinion = gamer.getEnemy().findAllMinion();
        int damage1 = gamer.getGamerSpellDamage() + DMG_1;
        int damage2 = gamer.getGamerSpellDamage() + DMG_2;
        target.beHurt(damage2);
        for (Minion minion : allMinion) {
            if (minion != target){
                minion.beHurt(damage1);
            }
        }
    }
}

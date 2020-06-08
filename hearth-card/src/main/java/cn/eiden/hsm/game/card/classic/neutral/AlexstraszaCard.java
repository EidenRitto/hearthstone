package cn.eiden.hsm.game.card.classic.neutral;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.card.defs.expert1.neutral.Alexstrasza;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.hero.HeroMinion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/8 21:48
 */
@TargetScope(classScope = HeroMinion.class)
public class AlexstraszaCard extends Alexstrasza {
    /**
     * "<b>战吼：</b>\n"
     * + "将一方英雄的剩余生命值变为15。"
     */
    @Override
    protected Battle selfBattleCry() {
        return (e,t) -> t.setHealth(15);
    }
}

package cn.eiden.hsm.game.card.sch.neutral;

import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.PlaguedProtodrake;
import cn.eiden.hsm.game.keyword.DeathRattle;

/**
 * 魔药始祖龙
 * @author Eiden J.P Zhou
 * @date 2020/8/10 14:26
 */
public class PlaguedProtodrakeCard extends PlaguedProtodrake {
    /**
     * "<b>亡语：</b>随机召唤一个法力值消耗为（7）的随从。"
     */
    @Override
    protected DeathRattle selfDeathRattle() {
        return s -> s.getOwner().addMinion(((AbstractMinionCard)CardFactory.buildCard()
                .cardType(CardType.MINION).cost(7).randomBuild()).createMinion());
    }
}

package cn.eiden.hsm.game.card.sch.neutral;

import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.SneakyDelinquent;
import cn.eiden.hsm.game.keyword.DeathRattle;

/**
 * 少年惯偷
 * @author Eiden J.P Zhou
 * @date 2020/8/10 14:47
 */
public class SneakyDelinquentCard extends SneakyDelinquent {
    /**
     * "<b>潜行，亡语：</b>将一个3/1并具有<b>潜行</b>的幽灵置入你的手牌。"
     */
    @Override
    protected DeathRattle selfDeathRattle() {
        return s->s.getOwner().getHand().addHandsCard(CardFactory.getCardById(59915));
    }
}

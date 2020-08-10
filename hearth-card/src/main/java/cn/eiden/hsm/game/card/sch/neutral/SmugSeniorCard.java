package cn.eiden.hsm.game.card.sch.neutral;

import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.SmugSenior;
import cn.eiden.hsm.game.keyword.DeathRattle;

/**
 * 浮夸的大四学长
 * @author Eiden J.P Zhou
 * @date 2020/8/10 14:39
 */
public class SmugSeniorCard extends SmugSenior {
    /**
     * "<b>嘲讽，亡语：</b>将一个5/7并具有<b>嘲讽</b>的幽灵置入你的手牌。"
     */
    @Override
    protected DeathRattle selfDeathRattle() {
        return s->s.getOwner().getHand().addHandsCard(CardFactory.getCardById(59917));
    }
}

package cn.eiden.hsm.game.card.sch.neutral;

import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.FishyFlyer;
import cn.eiden.hsm.game.keyword.DeathRattle;

/**
 * 鱼人飞骑
 * @author Eiden J.P Zhou
 * @date 2020/8/10 14:51
 */
public class FishyFlyerCard extends FishyFlyer {
    /**
     * "<b>突袭，亡语：</b>将一个4/3并具有<b>突袭</b>的幽灵置入你的手牌。"
     */
    @Override
    protected DeathRattle selfDeathRattle() {
        return s->s.getOwner().getHand().addHandsCard(CardFactory.getCardById(59912));
    }
}

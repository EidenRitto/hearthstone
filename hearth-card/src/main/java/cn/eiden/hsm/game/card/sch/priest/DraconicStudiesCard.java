package cn.eiden.hsm.game.card.sch.priest;

import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.priest.DraconicStudies;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.rule.rules.NextCommonCostReduceRule;

/**
 * 龙族研习
 * @author Eiden J.P Zhou
 * @date 2020/8/7 23:24
 */
public class DraconicStudiesCard extends DraconicStudies {
    /**
     * "<b>发现</b>一张龙牌。你的下一张龙牌法力值消耗减少（1）点。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        //发现
        gamer.discoverChooseOne(() -> CardFactory.buildCard()
                //随从牌
                .cardType(CardType.SPELL)
                //龙族
                .race(Race.DRAGON)
                .discover());
        gamer.addRule(new NextCommonCostReduceRule(1,CardType.MINION,(card -> ((AbstractMinionCard)card).getRace() == Race.DRAGON)));
    }
}

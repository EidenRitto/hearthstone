package cn.eiden.hsm.game.card.sch.druid;

import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.druid.NatureStudies;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.rule.rules.NextCommonCostReduceRule;

/**
 * @author Eiden J.P Zhou
 * @date 2020/8/7 17:01
 */
public class NatureStudiesCard extends NatureStudies {
    /**
     * "<b>发现</b>一张法术牌。你的下一张法术牌法力值消耗减少（1）点。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        //发现
        gamer.discoverChooseOne(() -> CardFactory.buildCard()
                //当前职业的
                .cardClass(gamer.getHero().getCardClass())
                //法术牌
                .cardType(CardType.SPELL)
                .discover());
        gamer.addRule(new NextCommonCostReduceRule(1,CardType.SPELL));
    }
}

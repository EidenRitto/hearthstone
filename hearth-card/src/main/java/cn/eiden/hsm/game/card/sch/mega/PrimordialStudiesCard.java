package cn.eiden.hsm.game.card.sch.mega;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.GameTag;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.PrimordialStudies;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.rule.rules.NextCommonCostReduceRule;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 始生研习
 * @author Eiden J.P Zhou
 * @date 2020/8/9 15:46
 */
public class PrimordialStudiesCard extends PrimordialStudies {
    /**
     * "<b>发现</b>一张<b>法术伤害</b>随从牌。你的下一张法术伤害随从牌法力值消耗减少（1）点。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        //发现
        gamer.discoverChooseOne(() -> CardFactory.buildCard()
                //随从牌
                .cardType(CardType.MINION)
                //具有法术伤害标签
                .gameTags(GameTag.SPELLPOWER)
                .discover());
        gamer.addRule(new NextCommonCostReduceRule(1,CardType.MINION,
                card -> Arrays.stream(card.getClass().getAnnotation(Tags.class).value())
                        .boxed()
                        .collect(Collectors.toSet())
                        .contains(GameTag.SPELLPOWER.getCode())));
    }
}

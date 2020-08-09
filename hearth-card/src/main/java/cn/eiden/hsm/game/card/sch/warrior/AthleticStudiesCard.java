package cn.eiden.hsm.game.card.sch.warrior;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.GameTag;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.warrior.AthleticStudies;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.rule.rules.NextCommonCostReduceRule;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 体能研习
 * @author Eiden J.P Zhou
 * @date 2020/8/8 0:39
 */
public class AthleticStudiesCard extends AthleticStudies {
    /**
     * "<b>发现</b>一张<b>突袭</b>随从牌。你的下一张<b>突袭</b>随从牌法力值消耗减少（1）点。"
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
                //具有突袭标签
                .gameTags(GameTag.RUSH)
                .discover());
        gamer.addRule(new NextCommonCostReduceRule(1,CardType.MINION,
                card -> Arrays.stream(card.getClass().getAnnotation(Tags.class).value())
                        .boxed()
                        .collect(Collectors.toSet())
                        .contains(GameTag.RUSH.getCode())));
    }
}

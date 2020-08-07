package cn.eiden.hsm.game.card.sch.neutral;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.PotionOfIllusion;
import cn.eiden.hsm.game.minion.Minion;

import java.util.List;
import java.util.Objects;

/**
 * 幻觉药水
 * @author Eiden J.P Zhou
 * @date 2020/8/7 14:58
 */
public class PotionOfIllusionCard extends PotionOfIllusion {
    /**
     * "将你的所有随从的1/1的复制置入你的手牌，并使其法力值消耗变为（1）点。"
     * @param gamer 当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        //拿到己方场上所有的随从
        List<Minion> minions = gamer.getMinions();
        minions.stream()
                //转化成他们对应的随从卡牌ID
                .map(Minion::getCardId)
                .mapToInt(Integer::parseInt)
                .boxed()
                //从卡牌工厂拿到卡牌ID对应的卡牌
                .map(CardFactory::getCardById)
                //我们能断定生成的卡牌一定是随从，强转为随从卡
                .map(e -> (AbstractMinionCard) e)
                //过滤掉空（理论上不存在空）
                .filter(Objects::nonNull)
                //把每一张随从卡的牌面设置1/1，费用为(1)
                .peek(e -> {
                    e.setCost(1);
                    e.setAtk(1L);
                    e.setHealth(1L);
                })
                //添加进手牌
                .forEach(e -> gamer.getHand().addHandsCard(e));
    }
}

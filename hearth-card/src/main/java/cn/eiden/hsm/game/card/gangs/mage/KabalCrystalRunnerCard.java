package cn.eiden.hsm.game.card.gangs.mage;

import cn.eiden.hsm.game.card.AbstractSecretCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.gangs.mage.KabalCrystalRunner;

import java.util.List;

/**
 * 暗金教水晶侍女
 * @author Eiden J.P Zhou
 * @date 2020/6/4 10:45
 */
public class KabalCrystalRunnerCard extends KabalCrystalRunner {
    private static final int COST_REDUCE_TIMES = 2;

    @Override
    public int getCost() {
        List<Card> usedSecretCard = getOwner().getHistory().getUsedCardWithType(AbstractSecretCard.class);
        return super.getCost() - usedSecretCard.size() * COST_REDUCE_TIMES;
    }
}

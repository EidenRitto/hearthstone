package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.minion.MinionObject;

import cn.eiden.hsm.game.card.AbstractMinionCard;


/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/21
 *  持盾卫士卡牌
 *
 */
@Tags(cardClass = CardClass.NEUTRAL, cardSet = CardSet.CORE)
public class ShieldBearerCard extends AbstractMinionCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "嘲讽。";
    private static final String CARD_NAME = "持盾卫士";
    private static final Long ATTACK_VALUE = 0L;
    private static final Long HEALTH_LIMIT = 4L;


    public ShieldBearerCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.NEUTRAL, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

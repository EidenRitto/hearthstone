package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.minion.MinionObject;

import cn.eiden.hsm.game.card.AbstractMinionCard;


/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  小精灵卡牌
 * */
@Tags(cardClass = CardClass.NEUTRAL, cardSet = CardSet.CORE)
public class SmallElfCard extends AbstractMinionCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "小精灵";
    private static final Long ATTACK_VALUE = 1L;
    private static final Long HEALTH_LIMIT = 1L;


    public SmallElfCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.NEUTRAL, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

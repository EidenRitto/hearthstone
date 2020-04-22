package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.*;
import cn.eiden.hsm.game.objct.MinionObject;

import cn.eiden.hsm.game.card.AbstractMinionCard;


/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 *  淡水鳄卡牌
 * */
@Tags(cardClass = CardClass.NEUTRAL, cardSet = CardSet.CORE)
public class FreshwaterCrocodileCard extends AbstractMinionCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "淡水鳄";
    private static final Long ATTACK_VALUE = 2L;
    private static final Long HEALTH_LIMIT = 3L;
    private static final Race RACE = Race.BEAST;


    public FreshwaterCrocodileCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.NEUTRAL, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return new MinionObject(CARD_NAME,HEALTH_LIMIT,ATTACK_VALUE,RACE);
    }
}

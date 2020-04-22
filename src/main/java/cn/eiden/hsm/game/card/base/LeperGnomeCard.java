package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.objct.MinionObject;

import cn.eiden.hsm.game.card.AbstractMinionCard;


/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 *  麻风侏儒卡牌
 * */
@Tags(cardClass = CardClass.NEUTRAL, cardSet = CardSet.CORE)
public class LeperGnomeCard extends AbstractMinionCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "亡语：对敌方英雄造成2点伤害";
    private static final String CARD_NAME = "麻风侏儒";
    private static final Long ATTACK_VALUE = 1L;
    private static final Long HEALTH_LIMIT = 1L;

    public LeperGnomeCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.NEUTRAL, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

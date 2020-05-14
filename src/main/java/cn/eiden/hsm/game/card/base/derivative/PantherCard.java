package cn.eiden.hsm.game.card.base.derivative;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.game.minion.MinionObject;

import cn.eiden.hsm.game.card.AbstractMinionCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/20
 *  黑豹
 *
 */
public class PantherCard extends AbstractMinionCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "黑豹";
    private static final Long ATTACK_VALUE = 3L;
    private static final Long HEALTH_LIMIT = 2L;


    public PantherCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.DRUID, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

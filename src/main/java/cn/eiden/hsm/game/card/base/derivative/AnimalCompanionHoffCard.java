package cn.eiden.hsm.game.card.base.derivative;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.objct.MinionObject;

import cn.eiden.hsm.game.card.AbstractMinionCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *
 *
 */
public class AnimalCompanionHoffCard extends AbstractMinionCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "冲锋";
    private static final String CARD_NAME = "霍夫";
    private static final Long ATTACK_VALUE = 4L;
    private static final Long HEALTH_LIMIT = 2L;
    private static final Race RACE = Race.BEAST;


    public AnimalCompanionHoffCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.HUNTER, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

package cn.eiden.hsm.game.card.base.derivative;

import cn.eiden.hsm.game.objct.minion.base.derivative.AnimalCompanionHoff;
import cn.eiden.hsm.game.card.MinionCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * @Description:
 * @Modified By:
 */
public class AnimalCompanionHoffCard extends MinionCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "冲锋";
    private static final String CARD_NAME = "霍夫";
    private static final Long ATTACK_VALUE = 4L;
    private static final Long HEALTH_LIMIT = 2L;


    public AnimalCompanionHoffCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new AnimalCompanionHoff());
    }
}

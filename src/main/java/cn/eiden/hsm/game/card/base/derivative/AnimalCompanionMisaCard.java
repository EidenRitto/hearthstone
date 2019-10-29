package cn.eiden.hsm.game.card.base.derivative;

import cn.eiden.hsm.game.objct.minion.base.derivative.AnimalCompanionMisa;
import cn.eiden.hsm.game.card.MinionCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * @Description:
 * @Modified By:
 */
public class AnimalCompanionMisaCard extends MinionCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "嘲讽";
    private static final String CARD_NAME = "米莎";
    private static final Long ATTACK_VALUE = 4L;
    private static final Long HEALTH_LIMIT = 4L;


    public AnimalCompanionMisaCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new AnimalCompanionMisa());
    }
}

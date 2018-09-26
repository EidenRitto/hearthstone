package game.card.base.derivative;

import game.card.MinionCard;
import game.objct.base.derivative.AnimalCompanionReoque;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description:
 * @Modified By:
 */
public class AnimalCompanionReoqueCard extends MinionCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "其他随从获得+1攻击力";
    private static final String CARD_NAME = "雷欧克";
    private static final Long ATTACK_VALUE = 2L;
    private static final Long HEALTH_LIMIT = 4L;


    public AnimalCompanionReoqueCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new AnimalCompanionReoque());
    }
}

package game.card.base.derivative;

import game.card.MinionCard;
import game.objct.base.derivative.Panther;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/20
 * @Description:
 * @Modified By: 黑豹
 */
public class PantherCard extends MinionCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "黑豹";
    private static final Long ATTACK_VALUE = 3L;
    private static final Long HEALTH_LIMIT = 2L;


    public PantherCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new Panther());
    }
}

package game.card.base;

import game.card.MinionCard;
import game.objct.MinionObject;
import game.objct.base.FreshwaterCrocodile;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 * @description : 淡水鳄卡牌
 * */
public class FreshwaterCrocodileCard extends MinionCard{
    private static final int COST = 2;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "淡水鳄";
    private static final Long ATTACK_VALUE = 2L;
    private static final Long HEALTH_LIMIT = 3L;


    public FreshwaterCrocodileCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new FreshwaterCrocodile());
    }
}

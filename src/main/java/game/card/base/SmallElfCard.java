package game.card.base;

import game.card.MinionCard;
import game.objct.MinionObject;
import game.objct.base.SmallElf;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description : 小精灵卡牌
 * */
public class SmallElfCard extends MinionCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "小精灵";
    private static final Long ATTACK_VALUE = 1L;
    private static final Long HEALTH_LIMIT = 1L;


    public SmallElfCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new SmallElf());
    }
}

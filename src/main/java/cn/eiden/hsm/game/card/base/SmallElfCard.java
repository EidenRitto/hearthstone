package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.game.objct.minion.base.SmallElf;
import cn.eiden.hsm.game.card.MinionCard;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  小精灵卡牌
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

package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.game.objct.minion.base.hunter.TimberWolf;
import cn.eiden.hsm.game.card.MinionCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *
 *
 */
public class TimberWolfCard extends MinionCard{
    private static final int COST = 1;
    private static final String DESCRIPTION = "其他野兽获得+1攻击";
    private static final String CARD_NAME = "森林狼";
    private static final Long ATTACK_VALUE = 1L;
    private static final Long HEALTH_LIMIT = 1L;


    public TimberWolfCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new TimberWolf());
    }
}

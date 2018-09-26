package game.card.base;

import game.card.MinionCard;
import game.objct.minion.base.LeperGnome;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 * @description : 麻风侏儒卡牌
 * */
public class LeperGnomeCard extends MinionCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "亡语：对敌方英雄造成2点伤害";
    private static final String CARD_NAME = "麻风侏儒";
    private static final Long ATTACK_VALUE = 1L;
    private static final Long HEALTH_LIMIT = 1L;

    public LeperGnomeCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new LeperGnome());
    }
}

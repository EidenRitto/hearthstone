package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.objct.minion.base.LeperGnome;
import cn.eiden.hsm.game.card.AbstractMinionCard;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 *  麻风侏儒卡牌
 * */
public class LeperGnomeCard extends AbstractMinionCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "亡语：对敌方英雄造成2点伤害";
    private static final String CARD_NAME = "麻风侏儒";
    private static final Long ATTACK_VALUE = 1L;
    private static final Long HEALTH_LIMIT = 1L;

    public LeperGnomeCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Neutral, HEALTH_LIMIT, ATTACK_VALUE,new LeperGnome());
    }
}

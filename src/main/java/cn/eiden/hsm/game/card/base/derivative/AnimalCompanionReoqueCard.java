package cn.eiden.hsm.game.card.base.derivative;

import cn.eiden.hsm.game.objct.MinionObject;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.minion.base.derivative.AnimalCompanionReoque;
import cn.eiden.hsm.game.card.AbstractMinionCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *
 *
 */
public class AnimalCompanionReoqueCard extends AbstractMinionCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "其他随从获得+1攻击力";
    private static final String CARD_NAME = "雷欧克";
    private static final Long ATTACK_VALUE = 2L;
    private static final Long HEALTH_LIMIT = 4L;


    public AnimalCompanionReoqueCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Hunter, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

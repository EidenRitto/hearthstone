package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.minion.base.FreshwaterCrocodile;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.tags.Version;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 *  淡水鳄卡牌
 * */
@Tags(profession = Profession.Neutral,version = Version.BASE)
public class FreshwaterCrocodileCard extends AbstractMinionCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "淡水鳄";
    private static final Long ATTACK_VALUE = 2L;
    private static final Long HEALTH_LIMIT = 3L;


    public FreshwaterCrocodileCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Neutral, HEALTH_LIMIT, ATTACK_VALUE,new FreshwaterCrocodile());
    }
}

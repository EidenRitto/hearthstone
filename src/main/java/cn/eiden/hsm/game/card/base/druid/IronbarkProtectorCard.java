package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.objct.MinionObject;

import cn.eiden.hsm.game.card.AbstractMinionCard;


/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  埃隆巴克保护者
 *
 */
@Tags(cardClass = CardClass.DRUID, cardSet = CardSet.CORE)
public class IronbarkProtectorCard extends AbstractMinionCard {
    private static final int COST = 8;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "埃隆巴克保护者";
    private static final Long ATTACK_VALUE = 8L;
    private static final Long HEALTH_LIMIT = 8L;


    public IronbarkProtectorCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.DRUID, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

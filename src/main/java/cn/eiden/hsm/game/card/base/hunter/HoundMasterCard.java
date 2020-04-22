package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.objct.MinionObject;

import cn.eiden.hsm.game.card.AbstractMinionCard;


/**
 * @author : Eiden J.P Zhou
 * @date : 2018/11/21
 *
 *
 */
@Tags(cardClass = CardClass.HUNTER, cardSet = CardSet.CORE)
public class HoundMasterCard extends AbstractMinionCard {
    private static final int COST = 4;
    private static final String DESCRIPTION = "选择一个友方野兽，使其获得+2/+2和嘲讽";
    private static final String CARD_NAME = "驯兽师";
    private static final Long ATTACK_VALUE = 4L;
    private static final Long HEALTH_LIMIT = 3L;


    public HoundMasterCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.HUNTER, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

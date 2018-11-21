package game.card.base.hunter;

import game.card.MinionCard;
import game.objct.minion.base.hunter.HoundMaster;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/11/21
 * @Description:
 * @Modified By:
 */
public class HoundMasterCard extends MinionCard {
    private static final int COST = 4;
    private static final String DESCRIPTION = "选择一个友方野兽，使其获得+2/+2和嘲讽";
    private static final String CARD_NAME = "驯兽师";
    private static final Long ATTACK_VALUE = 4L;
    private static final Long HEALTH_LIMIT = 3L;


    public HoundMasterCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE, new HoundMaster());
    }
}

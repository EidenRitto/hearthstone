package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.objct.minion.base.druid.IronbarkProtector;
import cn.eiden.hsm.game.card.MinionCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * @Description: 埃隆巴克保护者
 * @Modified By:
 */
public class IronbarkProtectorCard extends MinionCard {
    private static final int COST = 8;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "埃隆巴克保护者";
    private static final Long ATTACK_VALUE = 8L;
    private static final Long HEALTH_LIMIT = 8L;


    public IronbarkProtectorCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new IronbarkProtector());
    }
}

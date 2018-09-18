package game.card.classic;

import game.card.ServantCard;
import game.objct.classic.BloodMageThalnos;


/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/18
 * @Description:
 * @Modified By: 血法
 */
public class BloodMageThalnosCard extends ServantCard{
    private static final int COST = 2;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "血法师萨尔诺斯";
    private static final Long ATTACK_VALUE = 1L;
    private static final Long HEALTH_LIMIT = 1L;

    public BloodMageThalnosCard() {
        super(COST, DESCRIPTION, CARD_NAME, HEALTH_LIMIT, ATTACK_VALUE,new BloodMageThalnos());
    }

}

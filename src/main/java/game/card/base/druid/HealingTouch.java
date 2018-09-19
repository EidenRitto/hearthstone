package game.card.base.druid;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/19
 * @Description:
 * @Modified By: 生命之触
 */
public class HealingTouch extends MagicCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "恢复8点生命值。";
    private static final String CARD_NAME = "生命之触";
    private static final long HEALTH_NUMBER = 8;

    public HealingTouch() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        gamer.recovery(HEALTH_NUMBER);
    }
}

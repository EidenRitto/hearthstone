package game.card.base;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/18
 * @Description:
 * @Modified By:
 */
public class MoonFireCard extends MagicCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "造成一点伤害。";
    private static final String CARD_NAME = "月火术";
    private static final int MAGIC_CRYSTAL = 0;

    public MoonFireCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){

    }
}

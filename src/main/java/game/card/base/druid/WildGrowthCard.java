package game.card.base.druid;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/19
 * @Description:
 * @Modified By: 野性成长
 */
public class WildGrowthCard extends MagicCard{
    private static final int COST = 2;
    private static final String DESCRIPTION = "获得一个空的法力水晶。";
    private static final String CARD_NAME = "野性成长";
    private static final int MAGIC_CRYSTAL = 1;

    public WildGrowthCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        gamer.addMagicCrystal(MAGIC_CRYSTAL);
    }
}

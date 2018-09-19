package game.card.base.druid;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/19
 * @Description:
 * @Modified By: 激活
 */
public class InnervateCard extends MagicCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "获得一个法力水晶。";
    private static final String CARD_NAME = "激活";
    private static final int MAGIC_CRYSTAL = 1;

    public InnervateCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        gamer.addMagicCrystalNow(MAGIC_CRYSTAL);
    }
}

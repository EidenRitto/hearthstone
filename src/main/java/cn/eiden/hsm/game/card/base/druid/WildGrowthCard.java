package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.MagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
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

package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *  野性成长
 *
 */
public class WildGrowthCard extends AbstractMagicCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "获得一个空的法力水晶。";
    private static final String CARD_NAME = "野性成长";
    private static final int MAGIC_CRYSTAL = 1;

    public WildGrowthCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Druid);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        gamer.getManaCrystal().addEmpty(MAGIC_CRYSTAL);
    }
}

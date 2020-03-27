package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *  激活
 *
 */
public class InnervateCard extends AbstractMagicCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "获得一个法力水晶。";
    private static final String CARD_NAME = "激活";
    private static final int MAGIC_CRYSTAL = 1;

    public InnervateCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Druid);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        gamer.getManaCrystal().addAlive(MAGIC_CRYSTAL);
    }
}

package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *
 * */
public class CoinCard extends AbstractMagicCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "获得一个法力水晶。";
    private static final String CARD_NAME = "幸运币";
    private static final int MAGIC_CRYSTAL = 1;

    public CoinCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Neutral);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        gamer.getManaCrystal().addAlive(MAGIC_CRYSTAL);
    }
}

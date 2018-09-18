package game.card.base;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description :
 * */
public class CoinCard extends MagicCard{
    private static final int COST = 0;
    private static final String DESCRIPTION = "获得一个法力水晶。";
    private static final String CARD_NAME = "幸运币";
    private static final int MAGIC_CRYSTAL = 1;

    public CoinCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer,GameObject target) {
        gamer.addMagicCrystalNow(MAGIC_CRYSTAL);
    }
}

package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.game.card.dynamic.core.neutral.TheCoin;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.Gamer;


/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *
 * */
public class CoinCard extends TheCoin {
    private static final int MAGIC_CRYSTAL = 1;

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getManaCrystal().addAlive(MAGIC_CRYSTAL);
    }
}

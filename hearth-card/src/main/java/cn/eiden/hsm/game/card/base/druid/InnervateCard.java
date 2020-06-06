package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.card.defs.core.druid.Innervate;
import cn.eiden.hsm.game.minion.Minion;

import cn.eiden.hsm.game.Gamer;


/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *  激活
 *
 */
public class InnervateCard extends Innervate {
    private static final int MAGIC_CRYSTAL = 1;

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getManaCrystal().addAlive(MAGIC_CRYSTAL);
    }
}

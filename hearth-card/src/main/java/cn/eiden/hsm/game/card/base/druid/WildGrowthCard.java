package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.card.defs.core.druid.WildGrowth;
import cn.eiden.hsm.game.minion.Minion;

import cn.eiden.hsm.game.Gamer;


/**
 * 野性成长
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *
 */
public class WildGrowthCard extends WildGrowth {

    private static final int MAGIC_CRYSTAL = 1;

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getManaCrystal().addEmpty(MAGIC_CRYSTAL);
    }
}

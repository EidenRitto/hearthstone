package cn.eiden.hsm.game.card.blacktemple.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.black_temple.druid.Overgrowth;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 过度生长
 * @author Eiden J.P Zhou
 * @date 2020/6/16 21:03
 */
public class OvergrowthCard extends Overgrowth {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getManaCrystal().addEmpty(2);
    }
}

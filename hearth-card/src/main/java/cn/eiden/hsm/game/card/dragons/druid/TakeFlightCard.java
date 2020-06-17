package cn.eiden.hsm.game.card.dragons.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.year_of_the_dragon.druid.TakeFlight;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 雏鹰起飞
 * @author Eiden J.P Zhou
 * @date 2020/6/17 9:19
 */
public class TakeFlightCard extends TakeFlight {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.drawCard(1);
    }
}

package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.expert1.druid.SummonAPanther;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 召唤猎豹
 * @author Eiden J.P Zhou
 * @date 2020/6/15 17:33
 */
public class SummonAPantherCard extends SummonAPanther {

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.addMinion(812);
    }
}

package cn.eiden.hsm.game.card.uldum.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.uldum.druid.Overflow;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/16 20:38
 */
public class OverflowCard extends Overflow {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        // TODO: 2020/6/16 恢复效果待补全
        gamer.drawCard(5);
    }
}

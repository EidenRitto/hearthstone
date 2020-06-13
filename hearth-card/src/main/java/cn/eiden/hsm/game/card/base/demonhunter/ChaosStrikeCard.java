package cn.eiden.hsm.game.card.base.demonhunter;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.demonhunter.ChaosStrike;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 混乱打击
 * @author Eiden J.P Zhou
 * @date 2020/6/13 15:09
 */
public class ChaosStrikeCard extends ChaosStrike {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.drawCard(1);
        gamer.getHero().addAttack(2);
    }
}

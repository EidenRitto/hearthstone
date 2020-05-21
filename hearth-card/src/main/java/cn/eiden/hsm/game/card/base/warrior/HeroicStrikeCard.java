package cn.eiden.hsm.game.card.base.warrior;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.warrior.HeroicStrike;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/21 17:19
 */
public class HeroicStrikeCard extends HeroicStrike {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getHero().addAttack(4);
    }
}

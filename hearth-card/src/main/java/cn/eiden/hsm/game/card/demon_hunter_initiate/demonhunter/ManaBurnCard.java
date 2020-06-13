package cn.eiden.hsm.game.card.demon_hunter_initiate.demonhunter;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.demon_hunter_initiate.demonhunter.ManaBurn;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 法力燃烧
 * @author Eiden J.P Zhou
 * @date 2020/6/13 16:22
 */
public class ManaBurnCard extends ManaBurn {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getEnemy().getManaCrystal().setSave(-2);
    }
}

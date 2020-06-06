package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.druid.SavageRoar;
import cn.eiden.hsm.game.minion.Minion;


import cn.eiden.hsm.game.rule.rules.AddAtkThisTurnRule;

/**
 * 野蛮咆哮
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 */
public class SavageRoarCard extends SavageRoar {
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.addRule(new AddAtkThisTurnRule(ADD_ATTACK));
    }
}

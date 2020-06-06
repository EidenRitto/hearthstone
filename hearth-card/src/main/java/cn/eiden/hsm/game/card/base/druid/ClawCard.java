package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.druid.Claw;
import cn.eiden.hsm.game.minion.Minion;


/**
 * 爪击
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 */
public class ClawCard extends Claw {
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;
    /**增加的护甲*/
    private static final long ADD_ARMOR = 2;

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getHero().addArmor(ADD_ARMOR);
        gamer.getHero().addAttack(ADD_ATTACK);
    }
}

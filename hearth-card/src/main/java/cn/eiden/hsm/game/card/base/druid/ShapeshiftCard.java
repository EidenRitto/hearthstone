package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.druid.Shapeshift;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 变形
 * @author Eiden J.P Zhou
 * @date 2020/6/6 17:29
 */
public class ShapeshiftCard extends Shapeshift {
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 1;
    /**增加的护甲*/
    private static final long ADD_ARMOR = 1;

    /**
     * "<b>英雄技能</b>\n"
     * + "本回合+1攻击力。  +1护甲值。"
     *
     * @param gamer  当期玩家角色
     * @param target 所指定目标
     */
    @Override
    public void powerEffect(Gamer gamer, Minion target) {
        gamer.getHero().addArmor(ADD_ARMOR);
        gamer.getHero().addAttack(ADD_ATTACK);
    }
}

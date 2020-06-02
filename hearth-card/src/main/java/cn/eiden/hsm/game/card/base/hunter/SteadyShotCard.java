package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.hunter.SteadyShot;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/18 16:48
 */
public class SteadyShotCard extends SteadyShot {
    /**
     * "<b>英雄技能</b>\n"
     * + "对敌方英雄造成$2点伤害。@<b>英雄技能</b>\n"
     * + "造成$2点伤害。"
     *
     * @param target 所指定目标
     */
    @Override
    public void powerEffect(Gamer gamer, Minion target) {
        gamer.getEnemy().getHero().beHurt(gamer.getHero(), 2);
    }
}

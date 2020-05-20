package cn.eiden.hsm.game.card.base.warrior;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.warrior.ArmorUp;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/18 17:42
 */
public class ArmorUpCard extends ArmorUp {
    /**
     * "<b>英雄技能</b>\n"
     * + "获得2点护甲值。"
     *
     * @param gamer  当期玩家角色
     * @param target 所指定目标
     */
    @Override
    public void powerEffect(Gamer gamer, Minion target) {
        gamer.getHero().addArmor(2);
    }
}

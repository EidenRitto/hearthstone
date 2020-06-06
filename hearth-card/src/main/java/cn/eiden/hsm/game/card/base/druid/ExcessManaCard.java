package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.core.druid.ExcessMana;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/6 15:19
 */
public class ExcessManaCard extends ExcessMana {
    /**
     * "抽一张牌。<i>（你最多可以拥有\n"
     * + "十个法力水晶。）</i>"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.drawCard(1);
    }
}

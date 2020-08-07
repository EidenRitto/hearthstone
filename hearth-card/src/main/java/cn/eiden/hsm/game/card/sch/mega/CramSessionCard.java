package cn.eiden.hsm.game.card.sch.mega;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.scholomance.mage.CramSession;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 考前刷夜
 *
 * @author Eiden J.P Zhou
 * @date 2020/8/7 14:32
 */
public class CramSessionCard extends CramSession {
    /**
     * "抽$1张牌<i>（受<b>法术伤害</b>加成影响）</i>。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int spellDamage = gamer.getGamerSpellDamage();
        gamer.drawCard(1 + spellDamage);
    }
}

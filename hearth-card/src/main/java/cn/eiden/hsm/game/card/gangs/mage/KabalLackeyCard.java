package cn.eiden.hsm.game.card.gangs.mage;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.gangs.mage.KabalLackey;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.rule.rules.NextSecretCostZeroRule;

/**
 * 暗金教侍从
 * @author Eiden J.P Zhou
 * @date 2020/5/27 22:19
 */
public class KabalLackeyCard extends KabalLackey {
    /**
     * "<b>战吼：</b>\n"
     * + "在本回合中，你使用的下一个<b>奥秘</b>的法力值消耗为（0）点。"
     */
    @Override
    protected Battle selfBattleCry() {
        return (e,t) -> e.getOwner().addRule(new NextSecretCostZeroRule());
    }

}

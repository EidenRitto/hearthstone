package cn.eiden.hsm.game.card.classic.mage;

import cn.eiden.hsm.game.card.defs.expert1.mage.KirinTorMage;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.rule.rules.NextSecretCostZeroRule;

/**
 * 肯瑞托法师
 * @author Eiden J.P Zhou
 * @date 2020/5/29 15:49
 */
public class KirinTorMageCard extends KirinTorMage {
    @Override
    protected Battle selfBattleCry() {
        return (e,t) -> e.getOwner().addRule(new NextSecretCostZeroRule());
    }
}

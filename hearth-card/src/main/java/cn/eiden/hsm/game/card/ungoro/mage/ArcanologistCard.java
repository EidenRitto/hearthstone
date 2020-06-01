package cn.eiden.hsm.game.card.ungoro.mage;

import cn.eiden.hsm.game.card.AbstractSecretCard;
import cn.eiden.hsm.game.card.defs.ungoro.mage.Arcanologist;
import cn.eiden.hsm.game.keyword.Battle;

/**
 * 秘法学家
 * @author Eiden J.P Zhou
 * @date 2020/6/1 15:01
 */
public class ArcanologistCard extends Arcanologist {
    @Override
    protected Battle selfBattleCry() {
        return (e,t) -> e.getOwner().drawCardOfType(AbstractSecretCard.class);
    }
}

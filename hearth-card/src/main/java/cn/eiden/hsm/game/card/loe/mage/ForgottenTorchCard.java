package cn.eiden.hsm.game.card.loe.mage;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.loe.mage.ForgottenTorch;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/3 19:32
 */
@TargetScope
public class ForgottenTorchCard extends ForgottenTorch {
    private static final int DMG = 3;
    private static final int REF_CARD_ID = 2997;

    /**
     * "造成$3点伤害。将一张可造成6点伤害的“炽烈的火把”洗入你的牌库。"
     *
     * @param gamer  当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int damage = DMG + gamer.getGamerSpellDamage();
        target.beHurt(damage);
        Card refCard = CardFactory.getCardById(REF_CARD_ID);
    }
}

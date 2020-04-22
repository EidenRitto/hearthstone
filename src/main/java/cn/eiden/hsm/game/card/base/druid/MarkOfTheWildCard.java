package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.objct.MinionObject;

import cn.eiden.hsm.game.objct.Minion;

import cn.eiden.hsm.output.OutputInfo;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  野性印记
 *
 */
@Tags(cardClass = CardClass.DRUID, cardSet = CardSet.CORE)
public class MarkOfTheWildCard extends AbstractMagicCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "让一个随从获得+2/+2并具有嘲讽。";
    private static final String CARD_NAME = "野性印记";
    /**增加的生命值*/
    private static final long ADD_HEALTH = 2;
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;
    public MarkOfTheWildCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.DRUID);
    }

    @Override
    @TargetScope(classScope = MinionObject.class)
    public void magicEffect(Gamer gamer, Minion target) {
        target.addAttack(ADD_ATTACK);
        target.addHealthLimit(ADD_HEALTH);
        target.addTaunt();
        OutputInfo.info(target.getMinionName()+"获得嘲讽.");
        OutputInfo.info("by野性印记.");
    }
}

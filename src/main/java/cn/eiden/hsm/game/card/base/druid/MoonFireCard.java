package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.minion.Minion;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;


/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/18
 *  月火术
 *
 */
@Tags(cardClass = CardClass.DRUID, cardSet = CardSet.CORE)
@TargetScope
public class MoonFireCard extends AbstractMagicCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "造成一点伤害。";
    private static final String CARD_NAME = "月火术";
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 1;

    public MoonFireCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.DRUID);
    }

    @Override
    public void magicEffect(Gamer gamer, Minion target){
        int damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        target.beHurt(damage);
    }
}

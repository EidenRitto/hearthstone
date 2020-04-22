package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.objct.MinionObject;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.objct.Minion;


/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *  自然平衡
 *
 */
@Tags(cardClass = CardClass.DRUID, cardSet = CardSet.CORE)
public class NaturalizeCard extends AbstractMagicCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "消灭一个随从，你的对手抽两张牌。";
    private static final String CARD_NAME = "自然平衡";
    private static final int DRAW_CARDS = 2;

    public NaturalizeCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.HUNTER);
    }

    @Override
    @TargetScope(classScope = MinionObject.class)
    public void magicEffect(Gamer gamer, Minion target) {
        if (gamer.getMinions().contains(target)) {
            gamer.deathMinion(gamer.getMinions().indexOf(target));
        }else if (gamer.getEnemy().getMinions().contains(target)){
            gamer.getEnemy().deathMinion(gamer.getEnemy().getMinions().indexOf(target));
        }
        gamer.getEnemy().drawCard(DRAW_CARDS);
    }
}

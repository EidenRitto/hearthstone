package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.minion.Minion;

import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.tags.Stand;


/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  猎人标记
 *
 */
@Tags(cardClass = CardClass.HUNTER, cardSet = CardSet.CORE)
@TargetScope(classScope = MinionObject.class ,stand = Stand.ALL)
public class HuntersMark extends AbstractMagicCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "使一个随从生命值变为1。";
    private static final String CARD_NAME = "猎人标记";
    /**生命值改变*/
    private static final int HEALTH_CHANGE = 1;


    public HuntersMark() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.HUNTER);
    }

    @Override
    public void magicEffect(Gamer gamer, Minion target){
        if (target instanceof MinionObject){
            target.changeHealth(HEALTH_CHANGE);
        }
    }
}

package cn.eiden.hsm.game.card.classic.druid;

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
 * 自然印记
 */
@Tags(cardClass = CardClass.DRUID, cardSet = CardSet.CORE)
public class MarkOfNatureCard extends AbstractMagicCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "抉择：让一个随从获得+4攻击力;或者获得+4生命值并具有嘲讽。";
    private static final String CARD_NAME = "自然印记";
    /**增加的生命值*/
    private static final long ADD_HEALTH = 4;
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 4;

    public MarkOfNatureCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.HUNTER);
    }

    @Override
    @TargetScope(classScope = MinionObject.class)
    public void magicEffect(Gamer gamer, Minion target) {
        if (gamer.getChooseOne()==0){
            //+4攻击力
            target.addAttack(ADD_ATTACK);
            OutputInfo.info("by自然印记.");
        }else if (gamer.getChooseOne()==1){
            //+4生命值并获得嘲讽
            target.addHealthLimit(ADD_HEALTH);
            target.addTaunt();
            OutputInfo.info(target.getMinionName()+"获得嘲讽.");
            OutputInfo.info("by自然印记.");
        }
        gamer.setChooseOne(-1);
    }
}

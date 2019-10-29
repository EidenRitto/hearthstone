package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.MagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * @Description: 野性印记
 * @Modified By:
 */
public class MarkOfTheWildCard extends MagicCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "让一个随从获得+2/+2并具有嘲讽。";
    private static final String CARD_NAME = "野性印记";
    /**增加的生命值*/
    private static final long ADD_HEALTH = 2;
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;
    public MarkOfTheWildCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        if (target instanceof AbstractMinionObject){
            AbstractMinionObject targetMinion = (AbstractMinionObject)target;
            targetMinion.addAttack(ADD_ATTACK);
            targetMinion.addHealthLimit(ADD_HEALTH);
            targetMinion.setTaunt(true);
            System.out.println(targetMinion.getMinionName()+"获得嘲讽.");
            System.out.println("by野性印记.");
        }

    }
}

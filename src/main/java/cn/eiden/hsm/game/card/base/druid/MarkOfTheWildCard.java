package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  野性印记
 *
 */
@Slf4j
public class MarkOfTheWildCard extends AbstractMagicCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "让一个随从获得+2/+2并具有嘲讽。";
    private static final String CARD_NAME = "野性印记";
    /**增加的生命值*/
    private static final long ADD_HEALTH = 2;
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;
    public MarkOfTheWildCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Druid);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        if (target instanceof AbstractMinionObject){
            AbstractMinionObject targetMinion = (AbstractMinionObject)target;
            targetMinion.addAttack(ADD_ATTACK);
            targetMinion.addHealthLimit(ADD_HEALTH);
            targetMinion.setTaunt(true);
            log.info(targetMinion.getMinionName()+"获得嘲讽.");
            log.info("by野性印记.");
        }

    }
}

package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * 自然印记
 */
@Slf4j
public class MarkOfNatureCard extends AbstractMagicCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "抉择：让一个随从获得+4攻击力;或者获得+4生命值并具有嘲讽。";
    private static final String CARD_NAME = "自然印记";
    /**增加的生命值*/
    private static final long ADD_HEALTH = 4;
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 4;

    public MarkOfNatureCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Hunter);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        if (target instanceof AbstractMinionObject){
            AbstractMinionObject targetMinion = (AbstractMinionObject)target;
            if (gamer.getChooseOne()==0){
                //+4攻击力
                targetMinion.addAttack(ADD_ATTACK);
                log.info("by自然印记.");
            }else if (gamer.getChooseOne()==1){
                //+4生命值并获得嘲讽
                targetMinion.addHealthLimit(ADD_HEALTH);
                targetMinion.setTaunt(true);
                log.info(targetMinion.getMinionName()+"获得嘲讽.");
                log.info("by自然印记.");
            }
            gamer.setChooseOne(-1);
        }

    }
}

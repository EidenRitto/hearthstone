package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  奥术射击
 *
 */
public class ArcaneShotCard extends AbstractMagicCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "造成2点伤害。";
    private static final String CARD_NAME = "奥术射击";
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 2;

    public ArcaneShotCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Hunter);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){
        int damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        if (target instanceof AbstractMinionObject){
            ((AbstractMinionObject)target).beHurt(damage);
        }
    }
}

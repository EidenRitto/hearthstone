package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.MagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/18
 *  月火术
 *
 */
public class MoonFireCard extends MagicCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "造成一点伤害。";
    private static final String CARD_NAME = "月火术";
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 1;

    public MoonFireCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){
        int damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        if (target instanceof AbstractMinionObject){
            ((AbstractMinionObject)target).beHurt(damage);
        }
    }
}

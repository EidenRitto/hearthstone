package game.card.base.druid;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/18
 * @Description:
 * @Modified By: 月火术
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
        if (target instanceof Gamer){
            ((Gamer)target).beHurt(damage);
        }
        if (target instanceof MinionObject){
            ((MinionObject)target).beHurt(damage);
        }
    }
}

package game.card.base.hunter;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description: 奥术射击
 * @Modified By:
 */
public class ArcaneShotCard extends MagicCard{
    private static final int COST = 1;
    private static final String DESCRIPTION = "造成2点伤害。";
    private static final String CARD_NAME = "奥术射击";
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 2;

    public ArcaneShotCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){
        int damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        if (target instanceof MinionObject){
            ((MinionObject)target).beHurt(damage);
        }
    }
}

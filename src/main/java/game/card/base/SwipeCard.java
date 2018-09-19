package game.card.base;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;
import game.objct.ServantObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/19
 * @Description:
 * @Modified By: 德鲁伊法术-横扫
 */
public class SwipeCard extends MagicCard{
    private static final int COST = 4;
    private static final String DESCRIPTION = "对一个敌人造成4点伤害，对其他敌人造成1点伤害";
    private static final String CARD_NAME = "横扫";
    /**初始主伤害*/
    private static final int MAGIC_DAMAGE_MAIN = 4;
    /**初始副伤害*/
    private static final int MAGIC_DAMAGE_OTHER = 1;

    public SwipeCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){
        int gamerSpellDamage = gamer.getGamerSpellDamage();
        int damageMain = MAGIC_DAMAGE_MAIN + gamerSpellDamage;
        int damageOther = MAGIC_DAMAGE_OTHER + gamerSpellDamage;
        if (target instanceof Gamer){
            ((Gamer)target).beHurt(damageMain);
            ((Gamer)target).getServants().forEach(servantObject -> servantObject.beHurt(damageOther));
        }
        if (target instanceof ServantObject){
            gamer.getEnemy().beHurt(damageOther);
            gamer.getEnemy().getServants().forEach(servantObject -> {
                if (servantObject == target){
                    servantObject.beHurt(damageMain);
                }else {
                    servantObject.beHurt(damageOther);
                }
            });
        }
    }
}

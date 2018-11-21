package game.card.base.hunter;

import game.Gamer;
import game.card.MagicCard;
import game.objct.Ethnicity;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/11/21
 * @Description: 杀戮命令
 * @Modified By:
 */
public class KillCommandCard extends MagicCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "造成3点伤害。如果你控制野兽，则造成5点伤害";
    private static final String CARD_NAME = "杀戮命令";
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 3;
    /**条件下初始伤害*/
    private static final int MAGIC_DAMAGE_PLUS = 5;

    public KillCommandCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){
        int damage;
        //检查是否有野兽
        if (gamer.checkHaveEthnicity(Ethnicity.Beast)){
            damage = MAGIC_DAMAGE_PLUS + gamer.getGamerSpellDamage();
        }else {
            damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        }
        if (target instanceof MinionObject){
            ((MinionObject)target).beHurt(damage);
        }
        System.out.println("使用"+CARD_NAME+"对"+((MinionObject)target).getMinionName()+"造成"+damage+"点伤害");
    }
}

package game.card.base.druid;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description:
 * @Modified By: 自然印记
 */
public class MarkOfNatureCard extends MagicCard{
    private static final int COST = 3;
    private static final String DESCRIPTION = "抉择：让一个随从获得+4攻击力;或者获得+4生命值并具有嘲讽。";
    private static final String CARD_NAME = "自然印记";
    /**增加的生命值*/
    private static final long ADD_HEALTH = 4;
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 4;

    public MarkOfNatureCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        if (target instanceof MinionObject){
            MinionObject targetMinion = (MinionObject)target;
            if (gamer.getChooseOne()==0){
                //+4攻击力
                targetMinion.addAttack(ADD_ATTACK);
                System.out.println("by自然印记.");
            }else if (gamer.getChooseOne()==1){
                //+4生命值并获得嘲讽
                targetMinion.addHealthLimit(ADD_HEALTH);
                targetMinion.setTaunt(true);
                System.out.println(targetMinion.getMinionName()+"获得嘲讽.");
                System.out.println("by自然印记.");
            }
            gamer.setChooseOne(-1);
        }

    }
}

package game.card.classic.druid;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;
import game.objct.base.derivative.Panther;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/19
 * @Description: 野性之力
 * @Modified By:
 */
public class PowerOfTheWildCard extends MagicCard{
    private static final int COST = 2;
    private static final String DESCRIPTION = "抉择:你的随从获得+1/+1;或者召唤一个3/2猎豹。";
    private static final String CARD_NAME = "野性之力";
    private static final long ADD_HEALTH = 1;
    private static final long ADD_ATTACK = 1;

    public PowerOfTheWildCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        if (gamer.getChooseOne()==0){
            //+1/+1
            gamer.buffYourAllMinions(ADD_ATTACK,ADD_HEALTH);
            System.out.println("野性之力+1/+1.");
        }else if (gamer.getChooseOne()==1){
            //召唤3/2猎豹
            gamer.addMinion(new Panther());
            System.out.println("召唤3/2的黑豹.");
        }
        gamer.setChooseOne(-1);
    }
}

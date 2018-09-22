package game.card.base.hunter;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description: 猎人标记
 * @Modified By:
 */
public class HuntersMark extends MagicCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "使一个随从生命值变为1。";
    private static final String CARD_NAME = "猎人标记";
    /**生命值改变*/
    private static final int HEALTH_CHANGE = 1;


    public HuntersMark() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){
        if (target instanceof MinionObject){
            MinionObject minion = (MinionObject)target;
            minion.changeHealth(HEALTH_CHANGE);
        }
    }
}

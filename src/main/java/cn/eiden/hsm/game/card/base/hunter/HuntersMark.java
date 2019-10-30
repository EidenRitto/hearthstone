package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.MagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  猎人标记
 *
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
        if (target instanceof AbstractMinionObject){
            AbstractMinionObject minion = (AbstractMinionObject)target;
            minion.changeHealth(HEALTH_CHANGE);
        }
    }
}

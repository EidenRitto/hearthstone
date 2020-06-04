package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/26 17:31
 */
public class EndTurnOrder extends AbstractOrder implements Order{

    @Override
    public void execute() {
        gamer.endTurn();
    }

    public EndTurnOrder(Gamer gamer) {
        super(gamer);
    }
}

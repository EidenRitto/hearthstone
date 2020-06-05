package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import lombok.AllArgsConstructor;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/8 9:16
 */
public class StartTurnOrder extends AbstractOrder implements Order{

    @Override
    public void execute() {
        gamer.newTurnStart();
        gamer.getState();
    }

    public StartTurnOrder(Gamer gamer) {
        super(gamer);
    }
}

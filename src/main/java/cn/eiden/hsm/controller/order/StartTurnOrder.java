package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import lombok.AllArgsConstructor;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/8 9:16
 */
@AllArgsConstructor
public class StartTurnOrder implements Order{
    private Gamer gamer;

    @Override
    public void execute() {
        gamer.newTurnStart();
        gamer.getState();
    }
}

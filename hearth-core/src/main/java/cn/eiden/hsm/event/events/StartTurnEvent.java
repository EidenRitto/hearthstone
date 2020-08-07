package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;

/**
 * @author Eiden J.P Zhou
 * @date 2020/8/6 17:43
 */
public class StartTurnEvent extends AbstractEvent {
    public StartTurnEvent(Gamer owner) {
        super(owner);
    }
}

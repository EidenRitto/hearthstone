package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/26 17:16
 */
public class EndTurnEvent extends AbstractEvent {
    public EndTurnEvent(Gamer owner) {
        super(owner);
    }
}

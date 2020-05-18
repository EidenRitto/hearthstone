package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;

/**
 * 战场改变事件
 * 在战场随从改变时触发
 * @author Eiden J.P Zhou
 * @date 2020/3/16 17:03
 */
public class BattlefieldChangeEvent extends AbstractEvent {

    public BattlefieldChangeEvent(Gamer owner) {
        super(owner);
    }
}

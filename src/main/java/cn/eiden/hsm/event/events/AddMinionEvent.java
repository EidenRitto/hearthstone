package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;
import lombok.Getter;
import lombok.Setter;

/**
 * 添加随从事件
 * @author Eiden J.P Zhou
 * @date 2020/3/30 16:30
 */
public class AddMinionEvent extends AbstractEvent {
    @Setter
    @Getter
    private Minion minion;

    public AddMinionEvent(Gamer owner) {
        super(owner);
    }

    public AddMinionEvent(Gamer owner, Minion minion) {
        super(owner);
        this.minion = minion;
    }
}

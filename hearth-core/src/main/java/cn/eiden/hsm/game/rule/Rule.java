package cn.eiden.hsm.game.rule;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.game.Gamer;

import java.util.List;

/**
 * 规则
 * @author Eiden J.P ZHou
 * @date 2020/5/29 14:26
 */
public interface Rule {

    void effective(Gamer gamer);

    List<Class<? extends Event>> leaveEvents();

    default boolean leave(Class<? extends Event> eventType){
        return leaveEvents().contains(eventType);
    }
}

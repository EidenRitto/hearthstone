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

    /**
     * 效果
     * @param gamer 玩家
     */
    void effective(Gamer gamer);

    /**
     * 规则移除事件
     * @return 触发事件
     */
    List<Class<? extends Event>> leaveEvents();

    /**
     * 克隆
     * @return 新rule
     */
    Rule clone();

    default boolean leave(Class<? extends Event> eventType){
        return leaveEvents().contains(eventType);
    }
}

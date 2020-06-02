package cn.eiden.hsm.event;

import cn.eiden.hsm.game.Gamer;

/**
 * @author Eiden J.P ZHou
 * @date 2020/5/29 14:34
 */
public interface Event {
    /**
     * 获取发出事件者
     * @return 事件发出者
     */
    Gamer getOwner();
}

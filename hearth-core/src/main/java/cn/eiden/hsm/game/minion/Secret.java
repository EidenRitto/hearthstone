package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;

/**
 * 奥秘
 * @author Eiden J.P Zhou
 * @date 2020/5/27 9:19
 */
public interface Secret {
    /**
     * 触发效果
     * @return 成功返回true
     * */
    boolean onSecret(AbstractEvent event);

    /**
     * 触发事件
     * @return 触发事件
     */
    Class<? extends AbstractEvent> triggerEvent();

    /**
     * 获取所有者
     * @return 所有者
     */
    Gamer getOwner();

    /**
     * 设置所有者
     * @param gamer 所有者
     */
    void setOwner(Gamer gamer);
}

package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.annotation.Priority;
import cn.eiden.hsm.game.objct.AbstractMinionObject;

/**
 * 光环
 * @author Eiden J.P Zhou
 * @date 2020/3/16 17:22
 */
@Priority(1)
public interface BuffHole {
    /**
     * 效果
     * @param selfMinion 自身随从
     */
    void doBuffHole(AbstractMinionObject selfMinion);
}

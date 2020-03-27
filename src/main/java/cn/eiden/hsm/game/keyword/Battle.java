package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.game.GameObject;

/**
 * 战吼接口
 * @author Eiden J.P Zhou
 * @date 2019/10/29 10:10
 */
public interface Battle {
    /**
     * 执行战吼
     * @param target 所选目标
     */
    void doBattle(GameObject target);
}

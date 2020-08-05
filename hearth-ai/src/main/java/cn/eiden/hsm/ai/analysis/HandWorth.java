package cn.eiden.hsm.ai.analysis;

import cn.eiden.hsm.game.Hand;

/**
 * @author Eiden J.P Zhou
 * @date 2020/7/3 17:14
 */
public interface HandWorth {
    /**
     * 分析
     * @param hand
     * @return 价值
     */
    int analysis(Hand hand);
}

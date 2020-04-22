package cn.eiden.hsm.game.card;

import cn.eiden.hsm.enums.CardClass;

/**
 * 卡牌
 * @author Eiden J.P Zhou
 * @date 2020/3/19 9:42
 */
public interface Card {
    /**
     * 获取卡排名称
     * @return 卡排名称
     */
    String getCardName();

    /**
     * 获取费用
     * @return 费用
     */
    int getCost();

    /**
     * 获取描述
     * @return 描述
     */
    String getDescription();

    /**
     * 获取职业
     * @return 职业
     */
    CardClass getCardClass();
}

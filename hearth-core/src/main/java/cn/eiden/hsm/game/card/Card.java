package cn.eiden.hsm.game.card;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.game.GeneralItem;

/**
 * 卡牌
 * @author Eiden J.P Zhou
 * @date 2020/3/19 9:42
 */
public interface Card extends GeneralItem {
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

    /**
     * 设置规则强制花费
     * @param ruleForceCost 规则强制花费
     */
    void setRuleForceCost(Integer ruleForceCost);

    /**
     * 重设强制花费
     */
    void resetRuleForceCost();

    /**
     * 获取卡牌类型
     * @return 卡牌类型
     */
    CardType getCardType();

    /**
     * 是否不需要目标
     * @return 不需要目标返回true
     */
    default boolean isNoneTarget(){
        return !this.getClass().isAnnotationPresent(TargetScope.class);
    }
}

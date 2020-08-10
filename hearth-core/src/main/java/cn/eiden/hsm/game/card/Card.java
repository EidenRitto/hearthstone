package cn.eiden.hsm.game.card;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.listener.MinionListener;

import java.util.List;

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
     * 设置费用
     * @param cost 设置费用
     */
    void setCost(int cost);

    /**
     * 减少法力消耗
     * @param val 减少的费用
     */
    void reduceCost(int val);

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
     * 设置规则强制减少费用
     * @param ruleReduceCost 减少费用
     */
    void setRuleReduceCost(int ruleReduceCost);

    /**
     * 添加规则强制减少费用
     * @param ruleReduceCost 减少费用
     */
    void addRuleReduceCost(int ruleReduceCost);

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
     * 获取过载水晶数量
     * @return 过载水晶数量
     */
    int getOverload();

    /**
     * 是否是抉择卡牌
     * @return 有抉择选项返回true
     */
    boolean hasChooseOne();

    /**
     * 添加抉择选项
     * @param options  抉择选项列表
     */
    void addChooseOne(List<Card> options);

    /**
     * 获取选项
     * @return 抉择选项
     */
    List<Card> getOptions();

    /**
     * 获取卡牌id
     * @return 卡牌id
     */
    String getId();

    /**
     * 克隆
     * @return 返回一个副本,需要手动修改引用
     */
    Card clone();

    /**
     * 是否不需要目标
     * @return 不需要目标返回true
     */
    default boolean isNoneTarget(){
        return !this.getClass().isAnnotationPresent(TargetScope.class);
    }
}

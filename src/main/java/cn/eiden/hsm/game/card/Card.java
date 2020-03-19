package cn.eiden.hsm.game.card;

/**
 * 卡牌
 * @author 周晋平
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
     * 使用卡牌
     */
    void usingCard();
}

package cn.eiden.hsm.game.history;

import cn.eiden.hsm.game.card.Card;

import java.util.List;

/**
 * 历史记录
 * @author Eiden J.P Zhou
 * @date 2020/6/4 11:56
 */
public interface History {

    /**
     * 添加使用过的卡牌
     * @param card 卡牌
     */
    void addUsedCard(Card card);

    /**
     * 根据卡牌类型获取使用过的卡牌
     * @param cardClass 卡牌类型
     * @return 使用过的卡牌集合
     */
    List<Card> getUsedCardWithType(Class<? extends Card> cardClass);
}

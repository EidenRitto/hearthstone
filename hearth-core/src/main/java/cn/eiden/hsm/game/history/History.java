package cn.eiden.hsm.game.history;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.minion.Minion;

import java.util.List;

/**
 * 历史记录
 * @author Eiden J.P Zhou
 * @date 2020/6/4 11:56
 */
public interface History {

    /**
     * 记录第几回合使用的卡牌 </br>
     * 添加使用过的卡牌
     * @param card 卡牌
     * @param turnNum 回合数
     */
    void addUsedCard(Card card,int turnNum);

    /**
     * 根据卡牌类型获取使用过的卡牌
     * @param cardClass 卡牌类型
     * @return 使用过的卡牌集合
     */
    List<Card> getUsedCardWithType(Class<? extends Card> cardClass);

    /**
     * 获取某一回合使用过的卡牌数量
     * @param turnNum 回合数
     * @return 使用过的卡牌数量
     */
    int getUsedCardNumByTurnNum(int turnNum);


    int getCountByMinionName(Minion minion);

    /**
     * 克隆
     * @return 返回一个副本,需要手动修改引用
     */
    History clone();
}

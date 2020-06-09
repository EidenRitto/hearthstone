package cn.eiden.hsm.game.keyword;

import cn.eiden.hsm.game.card.Card;

import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/9 15:44
 */
@FunctionalInterface
public interface Discover {

    /**
     * 发现
     * @return 三张不同的卡牌
     */
    List<Card> discover();
}

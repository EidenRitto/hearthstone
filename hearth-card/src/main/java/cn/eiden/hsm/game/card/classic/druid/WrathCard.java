package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.expert1.druid.Wrath;

import java.util.ArrayList;
import java.util.List;

/**
 * 愤怒
 * @author Eiden J.P Zhou
 * @date 2020/6/8 12:58
 */
public class WrathCard extends Wrath {
    /**阳炎之怒 打3*/
    public static final int SOLAR_WRATH_ID = 253;
    /**自然之怒 抽1打1*/
    public static final int NATURE_WRATH_ID = 137;
    public WrathCard() {
        super();
        List<Card> cards = new ArrayList<>();
        cards.add(CardFactory.getCardById(SOLAR_WRATH_ID));
        cards.add(CardFactory.getCardById(NATURE_WRATH_ID));
        this.addChooseOne(cards);
    }
}

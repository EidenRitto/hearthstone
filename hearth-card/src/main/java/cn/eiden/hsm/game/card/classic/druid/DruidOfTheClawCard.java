package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.expert1.druid.DruidOfTheClaw;

import java.util.ArrayList;
import java.util.List;


/**
 * 利爪德鲁伊
 * @author Eiden J.P Zhou
 * @date 2020/6/8 12:38
 */
public class DruidOfTheClawCard extends DruidOfTheClaw {
    /**猎豹形态 冲锋*/
    public static final int CAT_ID = 63;
    /**熊形态 嘲讽*/
    public static final int BEAR_ID = 99;
    public DruidOfTheClawCard() {
        super();
        List<Card> cards = new ArrayList<>();
        cards.add(CardFactory.getCardById(CAT_ID));
        cards.add(CardFactory.getCardById(BEAR_ID));
        this.addChooseOne(cards);
    }
}

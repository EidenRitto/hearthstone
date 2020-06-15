package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.expert1.druid.PowerOfTheWild;

import java.util.ArrayList;
import java.util.List;

/**
 * 野性之力
 * @author Eiden J.P Zhou
 * @date 2020/6/15 17:24
 */
public class PowerOfTheWildCard extends PowerOfTheWild {

    public static final int SUMMON_A_PANTHER = 60;

    public static final int LEADER_OF_THE_PACK = 835;

    public PowerOfTheWildCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(CardFactory.getCardById(SUMMON_A_PANTHER));
        cards.add(CardFactory.getCardById(LEADER_OF_THE_PACK));
        this.addChooseOne(cards);
    }
}

package cn.eiden.hsm.game.card.dragons.druid;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.year_of_the_dragon.druid.RisingWinds;

import java.util.ArrayList;
import java.util.List;

/**
 * 乘风而起
 * @author Eiden J.P Zhou
 * @date 2020/6/17 9:09
 */
public class RisingWindsCard extends RisingWinds {
    /**雏鹰起飞*/
    public static final int TAKE_FLIGHT = 56060;
    /**猛禽飞掠*/
    public static final int SWOOP_IN = 56084;

    public RisingWindsCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(CardFactory.getCardById(TAKE_FLIGHT));
        cards.add(CardFactory.getCardById(SWOOP_IN));
        this.addChooseOne(cards);
    }
}

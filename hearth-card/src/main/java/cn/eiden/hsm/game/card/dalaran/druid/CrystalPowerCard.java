package cn.eiden.hsm.game.card.dalaran.druid;

import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.dalaran.druid.CrystalPower;

import java.util.ArrayList;
import java.util.List;

/**
 * 水晶之力
 * @author Eiden J.P Zhou
 * @date 2020/6/15 17:53
 */
public class CrystalPowerCard extends CrystalPower {
    public static final int PIERCING_THORNS = 51773;

    public static final int HEALING_BLOSSOM = 51775;

    public CrystalPowerCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(CardFactory.getCardById(PIERCING_THORNS));
        cards.add(CardFactory.getCardById(HEALING_BLOSSOM));
        this.addChooseOne(cards);
    }
}

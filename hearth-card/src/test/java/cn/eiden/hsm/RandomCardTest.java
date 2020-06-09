package cn.eiden.hsm;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/9 10:35
 */
public class RandomCardTest {

    public static void main(String[] args) {
        Card card = CardFactory.buildCard().cost(10).randomBuild();
        System.out.println(card.getCardName());

        for (int i = 0; i < 10; i++) {
            Card card1 = CardFactory.buildCard().cardClass(CardClass.MAGE).cardClass(CardClass.HUNTER).cardType(CardType.SPELL).randomBuild();
            System.out.println(card1.getCardName());
        }
    }
}

import game.card.MagicCard;
import game.card.base.CoinCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Integer i = new Integer(444);
        Integer j;
        j=i;
        i++;
        System.out.println(i);
        System.out.println(j);

        MagicCard magicCard = new CoinCard();
        MagicCard magicCard2 = magicCard;
        magicCard2.setCardName("xxxxxxxxxxxx");
        System.out.println(magicCard.getCardName());
        System.out.println(magicCard2.getCardName());
    }
}

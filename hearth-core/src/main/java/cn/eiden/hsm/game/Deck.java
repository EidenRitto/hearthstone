package cn.eiden.hsm.game;

import cn.eiden.hsm.game.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eiden J.P ZHou
 * @date 2020/4/16 17:53
 */
@Setter
@Getter
public class Deck {
    List<Card> deck = new ArrayList<>();

    public void addCard(Card card){
        deck.add(card);
    }
}

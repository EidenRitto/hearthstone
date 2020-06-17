package cn.eiden.hsm.game.card.blacktemple.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.black_temple.druid.FungalFortunes;
import cn.eiden.hsm.game.minion.Minion;

import java.util.List;

/**
 * 真菌宝藏
 * @author Eiden J.P Zhou
 * @date 2020/6/16 20:57
 */
public class FungalFortunesCard extends FungalFortunes {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        List<Card> cards = gamer.drawCard(3);
        for (Card card : cards) {
            if (card instanceof AbstractMinionCard){
                gamer.getHand().drop(card);
            }
        }
    }
}

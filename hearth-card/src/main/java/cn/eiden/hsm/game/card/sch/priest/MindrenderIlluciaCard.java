package cn.eiden.hsm.game.card.sch.priest;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.StartTurnEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.scholomance.priest.MindrenderIllucia;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.listener.HearthListener;

import java.util.List;

/**
 * 裂心者伊露希亚
 * @author Eiden J.P Zhou
 * @date 2020/8/6 17:06
 */
public class MindrenderIlluciaCard extends MindrenderIllucia{
    /**
     * "<b>战吼：</b>直到你的下个回合，和你的对手交换手牌和牌库。"
     */
    @Override
    protected Battle selfBattleCry() {
        return (s,t) -> {
            Gamer owner = s.getOwner();
            //交换hand
            swapHand(owner);
            //交换deck
            swapDeck(owner);
            //注册一个事件，这个事件在回合开始时触发，再交换回来
            owner.getEventManager().registerListener(new SwapHandDeckListener(owner));
        };
    }

    private static void swapDeck(Gamer gamer) {
        List<Card> deckCards = gamer.getDeckCards();
        List<Card> enemyDeckCards = gamer.getEnemy().getDeckCards();
        gamer.setDeckCards(enemyDeckCards);
        gamer.getEnemy().setDeckCards(deckCards);
    }

    private static void swapHand(Gamer gamer) {
        List<Card> hands = gamer.getHand().getCards();
        List<Card> enemyHands = gamer.getEnemy().getHand().getCards();
        gamer.getEnemy().getHand().setCards(hands);
        gamer.getHand().setCards(enemyHands);
    }

    public static class SwapHandDeckListener implements HearthListener {
        private Gamer gamer ;

        @EventHandler
        public void onEvent(StartTurnEvent event) {
            if (event.getOwner() == this.gamer){
                swapDeck(gamer);
                swapHand(gamer);
                gamer.getEventManager().removeListener(this);
            }
        }

        public SwapHandDeckListener(Gamer gamer) {
            this.gamer = gamer;
        }
    }
}

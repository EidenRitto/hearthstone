package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.keyword.Outcast;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/11 14:23
 */
public class OutcastListener implements HearthListener {
    @EventHandler
    public void onEvent(UseMinionCardFromHandEvent event) {
        Minion target = event.getTarget();
        Card card = event.getMinionCard();
        if (card instanceof Outcast && event.getOwner().isOutcastTrigger(card)) {
            ((Outcast) card).outcastEffect(event.getOwner(), target);
        }
    }
}

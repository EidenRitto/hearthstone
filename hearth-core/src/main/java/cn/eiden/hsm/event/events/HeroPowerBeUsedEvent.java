package cn.eiden.hsm.event.events;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractHeroPowerCard;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/12 17:30
 */
public class HeroPowerBeUsedEvent extends AbstractEvent {
    private AbstractHeroPowerCard heroPowerCard;
    private Minion target;

    public HeroPowerBeUsedEvent(Gamer owner, AbstractHeroPowerCard heroPowerCard, Minion target) {
        super(owner);
        this.heroPowerCard = heroPowerCard;
        this.target = target;
    }

    public AbstractHeroPowerCard getHeroPowerCard() {
        return heroPowerCard;
    }

    public Minion getTarget() {
        return target;
    }
}

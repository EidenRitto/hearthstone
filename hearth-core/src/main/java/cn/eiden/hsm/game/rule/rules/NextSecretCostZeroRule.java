package cn.eiden.hsm.game.rule.rules;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.EndTurnEvent;
import cn.eiden.hsm.event.events.UseSecretCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractSecretCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.rule.AbstractRule;
import cn.eiden.hsm.game.rule.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eiden J.P ZHou
 * @date 2020/5/29 15:52
 */
public class NextSecretCostZeroRule extends AbstractRule implements Rule {


    @Override
    public void effective(Gamer gamer) {
        List<Card> cards = gamer.getHand().getCards();
        cards.stream()
                .filter(e->e instanceof AbstractSecretCard)
                .forEach(e->e.setRuleForceCost(0));
    }

    @Override
    public List<Class<? extends Event>> leaveEvents() {
        List<Class<? extends Event>> list = new ArrayList<>();
        list.add(UseSecretCardFromHandEvent.class);
        list.add(EndTurnEvent.class);
        return list;
    }
}

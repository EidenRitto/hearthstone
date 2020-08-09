package cn.eiden.hsm.game.rule.rules;

import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.event.events.UseSpellCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.rule.AbstractRule;
import cn.eiden.hsm.game.rule.Rule;

import java.util.function.Predicate;

/**
 * @author Eiden J.P Zhou
 * @date 2020/8/7 17:10
 */
public class NextCommonCostReduceRule extends AbstractRule implements Rule {

    private int reduceCost;
    private CardType cardType;
    private Predicate<Card> minionPredicate;

    @Override
    public void effective(Gamer gamer) {
        if (cardType == CardType.SPELL){
            gamer.getHand().getCards().stream()
                    .filter(e -> e.getCardType() == cardType)
                    .forEach(e -> e.addRuleReduceCost(reduceCost));
        }else if (cardType == CardType.MINION){
            gamer.getHand().getCards().stream()
                    .filter(e -> e.getCardType() == cardType)
                    .filter(e -> minionPredicate.test(e))
                    .forEach(e -> e.addRuleReduceCost(reduceCost));
        }
    }


    @Override
    public Predicate<Event> leaveEvents() {
        return event -> {
            if (cardType == CardType.MINION) {
                if (event instanceof UseMinionCardFromHandEvent) {
                    UseMinionCardFromHandEvent useMinionEvent = (UseMinionCardFromHandEvent) event;
                    Card minionCard = useMinionEvent.getMinionCard();
                    return minionPredicate.test(minionCard);
                } else {
                    return false;
                }
            } else {
                return event instanceof UseSpellCardFromHandEvent;
            }
        };
    }

    public NextCommonCostReduceRule(int reduceCost, CardType cardType) {
        this.reduceCost = reduceCost;
        this.cardType = cardType;
    }

    public NextCommonCostReduceRule(int reduceCost, CardType cardType, Predicate<Card> minionPredicate) {
        this.reduceCost = reduceCost;
        this.cardType = cardType;
        this.minionPredicate = minionPredicate;
    }
}

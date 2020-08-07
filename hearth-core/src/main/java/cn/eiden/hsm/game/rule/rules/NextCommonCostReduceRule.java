package cn.eiden.hsm.game.rule.rules;

import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.event.events.UseSpellCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
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
    private Predicate<Minion> minionPredicate;

    @Override
    public void effective(Gamer gamer) {
        gamer.getHand().getCards().stream()
                .filter(e -> e.getCardType() == CardType.SPELL)
                .forEach(e->e.addRuleReduceCost(reduceCost));
    }


    @Override
    public Predicate<Event> leaveEvents() {
        return event -> {
            if (cardType == CardType.MINION){
                if (event instanceof UseMinionCardFromHandEvent){
                    UseMinionCardFromHandEvent useMinionEvent = (UseMinionCardFromHandEvent) event;
                    Minion minionObject = useMinionEvent.getMinionObject();
                    return minionPredicate.test(minionObject);
                }else {
                    return false;
                }
            }else {
                return event instanceof UseSpellCardFromHandEvent;
            }
        };
    }

    public NextCommonCostReduceRule(int reduceCost, CardType cardType) {
        this.reduceCost = reduceCost;
        this.cardType = cardType;
    }

    public NextCommonCostReduceRule(int reduceCost, CardType cardType, Predicate<Minion> minionPredicate) {
        this.reduceCost = reduceCost;
        this.cardType = cardType;
        this.minionPredicate = minionPredicate;
    }
}

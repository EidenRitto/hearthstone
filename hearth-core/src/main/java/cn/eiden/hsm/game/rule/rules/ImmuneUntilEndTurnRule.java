package cn.eiden.hsm.game.rule.rules;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.EndTurnEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.rule.AbstractRule;
import cn.eiden.hsm.game.rule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/2 17:29
 */
public class ImmuneUntilEndTurnRule extends AbstractRule implements Rule {
    @Override
    public void effective(Gamer gamer) {
        gamer.getHero().addImmune();
    }

    @Override
    public Predicate<Event> leaveEvents() {
        return event -> event instanceof EndTurnEvent;
    }
}

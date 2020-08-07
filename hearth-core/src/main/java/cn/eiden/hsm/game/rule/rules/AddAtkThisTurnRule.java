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
 * @date 2020/6/6 17:22
 */
public class AddAtkThisTurnRule extends AbstractRule implements Rule {
    private long addAtk;

    @Override
    public void effective(Gamer gamer) {
        gamer.getHero().addBuffAtk(addAtk);
        gamer.getMinions().forEach(e->e.addBuffAtk(addAtk));
    }

    @Override
    public Predicate<Event> leaveEvents() {
        return event -> event instanceof EndTurnEvent;
    }

    public AddAtkThisTurnRule(long addAtk) {
        this.addAtk = addAtk;
    }
}

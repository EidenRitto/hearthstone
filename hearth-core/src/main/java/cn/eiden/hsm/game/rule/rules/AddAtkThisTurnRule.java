package cn.eiden.hsm.game.rule.rules;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.EndTurnEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.rule.AbstractRule;
import cn.eiden.hsm.game.rule.Rule;

import java.util.ArrayList;
import java.util.List;

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
    public List<Class<? extends Event>> leaveEvents() {
        List<Class<? extends Event>> list = new ArrayList<>();
        list.add(EndTurnEvent.class);
        return list;
    }

    public AddAtkThisTurnRule(long addAtk) {
        this.addAtk = addAtk;
    }
}

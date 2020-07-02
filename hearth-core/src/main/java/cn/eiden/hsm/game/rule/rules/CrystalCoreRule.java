package cn.eiden.hsm.game.rule.rules;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.rule.AbstractRule;
import cn.eiden.hsm.game.rule.Rule;

import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/10 15:56
 */
public class CrystalCoreRule extends AbstractRule implements Rule {
    @Override
    public void effective(Gamer gamer) {

    }

    @Override
    public List<Class<? extends Event>> leaveEvents() {
        return null;
    }

    @Override
    public boolean leave(Class<? extends Event> eventType) {
        return false;
    }
}

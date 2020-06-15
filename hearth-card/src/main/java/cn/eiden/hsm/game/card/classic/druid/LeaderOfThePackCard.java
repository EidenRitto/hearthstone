package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.expert1.druid.LeaderOfThePack;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 兽群领袖
 * @author Eiden J.P Zhou
 * @date 2020/6/15 17:50
 */
public class LeaderOfThePackCard extends LeaderOfThePack {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getMinions().forEach(e->{e.addAttack(1);e.addHealthLimit(1);});
    }
}

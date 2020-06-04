package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.Minion;

import java.util.List;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/16 20:12
 */
public class HeroPowerOrder extends AbstractOrder implements Order {


    @Override
    public void execute() {
        if (gamer.getHero().getHeroPower().isNoneTarget()) {
            gamer.useHeroPower(null);
        } else {
            List<Minion> legitimateTarget = gamer.getLegitimateTarget(gamer.getHero().getHeroPower());
            Integer targetNum = getTargetNum(legitimateTarget, gamer);
            Minion targetMinion = legitimateTarget.get(targetNum);
            gamer.useHeroPower(targetMinion);
        }
    }

    public HeroPowerOrder(Gamer gamer) {
        super(gamer);
    }
}

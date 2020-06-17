package cn.eiden.hsm.game.card.dragons.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.year_of_the_dragon.druid.SwoopIn;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 乘风而起-2猛禽飞掠
 * @author Eiden J.P Zhou
 * @date 2020/6/17 9:20
 */
public class SwoopInCard extends SwoopIn {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.addMinion(56080);
    }
}

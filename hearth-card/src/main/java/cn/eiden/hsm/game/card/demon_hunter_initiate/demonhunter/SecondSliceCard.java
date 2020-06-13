package cn.eiden.hsm.game.card.demon_hunter_initiate.demonhunter;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.demon_hunter_initiate.demonhunter.SecondSlice;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 二次斩击
 * @author Eiden J.P Zhou
 * @date 2020/6/13 16:21
 */
public class SecondSliceCard extends SecondSlice {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getHero().addAttack(1);
    }
}

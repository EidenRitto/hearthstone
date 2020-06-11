package cn.eiden.hsm.game.card.blacktemple.demonhunter;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.black_temple.demonhunter.CrimsonSigilRunner;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 火色魔印奔行者
 * @author Eiden J.P Zhou
 * @date 2020/6/11 14:50
 */
public class CrimsonSigilRunnerCard extends CrimsonSigilRunner {
    @Override
    public void outcastEffect(Gamer gamer, Minion target) {
        gamer.drawCard(1);
    }
}

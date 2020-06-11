package cn.eiden.hsm.game.card.blacktemple.demonhunter;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.black_temple.demonhunter.SpectralSight;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 幽灵视觉
 * @author Eiden J.P Zhou
 * @date 2020/6/11 14:42
 */
public class SpectralSightCard extends SpectralSight {
    @Override
    public void baseEffect(Gamer gamer, Minion target) {
        gamer.drawCard(1);
    }

    @Override
    public void outcastEffect(Gamer gamer, Minion target) {
        gamer.drawCard(2);
    }
}

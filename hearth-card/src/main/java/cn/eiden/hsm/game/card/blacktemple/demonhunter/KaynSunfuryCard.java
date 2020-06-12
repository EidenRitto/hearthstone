package cn.eiden.hsm.game.card.blacktemple.demonhunter;

import cn.eiden.hsm.game.card.defs.black_temple.demonhunter.KaynSunfury;
import cn.eiden.hsm.game.keyword.Aura;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/12 17:48
 */
public class KaynSunfuryCard extends KaynSunfury {
    @Override
    protected Aura selfAura() {
        return s->s.hasReady();
    }
}

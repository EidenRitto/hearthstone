package cn.eiden.hsm.game.card.demon_hunter_initiate.demonhunter;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.demon_hunter_initiate.demonhunter.ConsumeMagic;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;

/**
 * 吞噬魔法
 * @author Eiden J.P Zhou
 * @date 2020/6/11 14:46
 */
@TargetScope(classScope = MinionObject.class)
public class ConsumeMagicCard extends ConsumeMagic {
    @Override
    public void baseEffect(Gamer gamer, Minion target) {
        target.silence();
    }

    @Override
    public void outcastEffect(Gamer gamer, Minion target) {
        target.silence();
        gamer.drawCard(1);
    }
}

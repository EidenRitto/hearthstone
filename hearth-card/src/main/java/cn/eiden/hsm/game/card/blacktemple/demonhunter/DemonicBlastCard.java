package cn.eiden.hsm.game.card.blacktemple.demonhunter;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.black_temple.demonhunter.DemonicBlast;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 恶魔冲击
 * @author Eiden J.P Zhou
 * @date 2020/6/12 17:40
 */
@TargetScope
public class DemonicBlastCard extends DemonicBlast {
    @Override
    public void powerEffect(Gamer gamer, Minion target) {
        target.beHurt(5);
        gamer.checkMinion();
        gamer.getEnemy().checkMinion();
    }
}

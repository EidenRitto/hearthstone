package cn.eiden.hsm.game.card.sch.hunter;

import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.hunter.BloatedPython;
import cn.eiden.hsm.game.keyword.DeathRattle;

import java.util.Objects;

/**
 * 饱腹巨蟒
 * @author Eiden J.P Zhou
 * @date 2020/8/7 16:28
 */
public class BloatedPythonCard extends BloatedPython {
    /**
     * "<b>亡语：</b>召唤一个4/4的倒霉的管理员。"
     */
    @Override
    protected DeathRattle selfDeathRattle() {
        return new MyDeathRattle();
    }

    public static class MyDeathRattle implements DeathRattle{
        @Override
        public void doDeathRattle(GeneralItem generalItem) {
            //倒霉的管理员
            AbstractMinionCard refCard = (AbstractMinionCard) CardFactory.getCardById(61173);
            generalItem.getOwner().addMinion(Objects.requireNonNull(refCard).createMinion());
        }
    }
}

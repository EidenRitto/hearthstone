package cn.eiden.hsm.game.card.kara.mage;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.card.defs.kara.mage.MedivhSValet;
import cn.eiden.hsm.game.keyword.Battle;

/**
 * 麦迪文的男仆
 * @author Eiden J.P Zhou
 * @date 2020/6/1 15:31
 */
@TargetScope
public class MedivhSValetCard extends MedivhSValet {
    private static final int DMG = 3;

    @Override
    protected Battle selfBattleCry() {
        return (s,t) -> {
            if (t!=null) {t.beHurt(s,DMG);}
        };
    }

    /**
     * 在没有奥秘时不需要选择目标</br>
     * 这张卡牌在某些条件下不需要选择目标，所以重写此方法
     * @return 是否不需要选择目标
     */
    @Override
    public boolean isNoneTarget() {
        return !this.getOwner().hasSecret();
    }
}

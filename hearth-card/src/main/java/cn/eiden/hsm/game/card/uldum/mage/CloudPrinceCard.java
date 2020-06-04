package cn.eiden.hsm.game.card.uldum.mage;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.card.defs.uldum.mage.CloudPrince;
import cn.eiden.hsm.game.keyword.Battle;

/**
 * 云雾王子
 * @author Eiden J.P Zhou
 * @date 2020/6/4 10:42
 */
@TargetScope
public class CloudPrinceCard extends CloudPrince {
    private static final int DMG = 6;

    @Override
    protected Battle selfBattleCry() {
        return (s,t) -> {
            if (t!=null) {t.beHurt(s,DMG);}
        };
    }

    @Override
    public boolean isNoneTarget() {
        return !this.getOwner().hasSecret();
    }
}

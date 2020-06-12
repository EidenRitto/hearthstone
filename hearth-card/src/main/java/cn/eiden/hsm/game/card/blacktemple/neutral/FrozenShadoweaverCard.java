package cn.eiden.hsm.game.card.blacktemple.neutral;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.card.defs.black_temple.neutral.FrozenShadoweaver;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.tags.Stand;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/12 17:46
 */
@TargetScope(stand = Stand.FOE)
public class FrozenShadoweaverCard extends FrozenShadoweaver {
    @Override
    protected Battle selfBattleCry() {
        return (s,t) -> t.freeze();
    }
}

package cn.eiden.hsm.game.card.sch.paladin;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.scholomance.paladin.GiftOfLuminance;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/8/14 14:41
 */
@TargetScope
public class GiftOfLuminanceCard extends GiftOfLuminance {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        // TODO: 2020/8/14 验证对地方随从使用的效果
    }
}

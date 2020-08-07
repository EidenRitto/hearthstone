package cn.eiden.hsm.game.card.sch.neutral;

import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.WandThief;
import cn.eiden.hsm.game.keyword.Combo;

/**
 * 魔杖窃贼
 * @author Eiden J.P Zhou
 * @date 2020/8/7 15:35
 */
public class WandThiefCard extends WandThief {
    /**
     * "<b>连击：</b><b>发现</b>一张法师法术牌。"
     */
    @Override
    protected Combo selfCombo() {
        return (s,t)->s.getOwner()
                //发现一张
                .discoverChooseOne(() -> CardFactory.buildCard()
                        //法师的
                        .cardClass(CardClass.MAGE)
                        //法术牌
                        .cardType(CardType.SPELL)
                        .discover());
    }
}

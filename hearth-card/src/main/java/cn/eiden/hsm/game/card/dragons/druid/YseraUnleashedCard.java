package cn.eiden.hsm.game.card.dragons.druid;

import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.dragons.druid.YseraUnleashed;
import cn.eiden.hsm.game.keyword.Battle;

/**
 * 觉醒巨龙伊瑟拉
 * @author Eiden J.P Zhou
 * @date 2020/6/17 10:20
 */
public class YseraUnleashedCard extends YseraUnleashed {
    @Override
    protected Battle selfBattleCry() {
        return (s,t) -> {
            for (int i = 0; i < 7; i++) {
                s.getOwner().shuffleCard(CardFactory.getCardById(55778));
            }
        };
    }
}

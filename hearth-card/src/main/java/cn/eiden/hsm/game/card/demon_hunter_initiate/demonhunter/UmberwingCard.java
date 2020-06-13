package cn.eiden.hsm.game.card.demon_hunter_initiate.demonhunter;

import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.demon_hunter_initiate.demonhunter.Umberwing;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 棕红之翼
 * @author Eiden J.P Zhou
 * @date 2020/6/13 14:31
 */
public class UmberwingCard extends Umberwing {
    @Override
    protected Battle selfBattleCry() {
        return (s,t) -> {
            AbstractMinionCard cardById = (AbstractMinionCard) CardFactory.getCardById(56592);
            assert cardById != null;
            Minion minion = cardById.createMinion();
            Minion minion2 = cardById.createMinion();
            s.getOwner().addMinion(minion);
            s.getOwner().addMinion(minion2);
        };
    }
}

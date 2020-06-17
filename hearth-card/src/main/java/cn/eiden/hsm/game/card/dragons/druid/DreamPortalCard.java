package cn.eiden.hsm.game.card.dragons.druid;

import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.dragons.druid.DreamPortal;
import cn.eiden.hsm.game.keyword.TopDeck;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 梦境之门
 * @author Eiden J.P Zhou
 * @date 2020/6/17 10:25
 */
public class DreamPortalCard extends DreamPortal implements TopDeck {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        AbstractMinionCard card = (AbstractMinionCard) CardFactory.buildCard()
                .cardType(CardType.MINION)
                .race(Race.DRAGON)
                .randomBuild();
        gamer.addMinion(card.createMinion());
    }

    @Override
    public void onDraw() {
        this.magicEffect(getOwner(), null);
    }
}

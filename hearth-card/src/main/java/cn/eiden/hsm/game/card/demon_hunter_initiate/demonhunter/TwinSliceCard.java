package cn.eiden.hsm.game.card.demon_hunter_initiate.demonhunter;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.demon_hunter_initiate.demonhunter.TwinSlice;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 双刃斩击
 * @author Eiden J.P Zhou
 * @date 2020/6/13 16:11
 */
public class TwinSliceCard extends TwinSlice {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getHero().addAttack(1);
        gamer.getHand().addHandsCard(CardFactory.getCardById(56935));
    }
}

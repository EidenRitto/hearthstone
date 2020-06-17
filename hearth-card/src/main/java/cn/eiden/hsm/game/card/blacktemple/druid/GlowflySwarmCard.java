package cn.eiden.hsm.game.card.blacktemple.druid;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.Hand;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.black_temple.druid.GlowflySwarm;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 萤火成群
 * @author Eiden J.P Zhou
 * @date 2020/6/17 9:02
 */
public class GlowflySwarmCard extends GlowflySwarm {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int num = 0;
        for (Card card : gamer.getHand().getCards()) {
            if (card instanceof AbstractMagicCard){
                num++;
            }
        }
        for (int i = 0; i < num; i++) {
            gamer.addMinion(56688);
        }
    }
}

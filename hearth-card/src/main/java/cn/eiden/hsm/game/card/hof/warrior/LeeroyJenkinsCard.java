package cn.eiden.hsm.game.card.hof.warrior;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.hof.neutral.LeeroyJenkins;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/18 17:46
 */
public class LeeroyJenkinsCard extends LeeroyJenkins {
    /**雏龙id*/
    private static int WhelpId = 1912;
    @Override
    protected Battle selfBattleCry() {
        return new BattleCry();
    }

    static public class BattleCry implements Battle {

        @Override
        public void doBattle(Gamer gamer, Minion self, Minion target) {
            AbstractMinionCard whelpCard = (AbstractMinionCard) CardFactory.getCardById(WhelpId);
            assert whelpCard != null;
            gamer.getEnemy().addMinion(whelpCard.createMinion());
            gamer.getEnemy().addMinion(whelpCard.createMinion());
        }
    }
}

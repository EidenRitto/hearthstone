package cn.eiden.hsm.game.card.hof.warrior;

import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.hof.neutral.LeeroyJenkins;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 火车王
 * @author Eiden J.P Zhou
 * @date 2020/5/18 17:46
 */
public class LeeroyJenkinsCard extends LeeroyJenkins {
    /**雏龙id*/
    private static final int WHELP_ID = 1912;
    @Override
    protected Battle selfBattleCry() {
        return new BattleCry();
    }

    static public class BattleCry implements Battle {
        @Override
        public void doBattle(Minion self, Minion target) {
            AbstractMinionCard whelpCard = (AbstractMinionCard) CardFactory.getCardById(WHELP_ID);
            assert whelpCard != null;
            self.getOwner().getEnemy().addMinion(whelpCard.createMinion());
            self.getOwner().getEnemy().addMinion(whelpCard.createMinion());
        }
    }
}

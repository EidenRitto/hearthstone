package cn.eiden.hsm.game.card.blacktemple.priest;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.black_temple.priest.Renew;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 复苏
 * @author Eiden J.P Zhou
 * @date 2020/6/9 17:13
 */
@TargetScope
public class RenewCard extends Renew {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        target.recoveryHp(3);
        gamer.discoverChooseOne(() -> CardFactory.buildCard().cardType(CardType.SPELL).discover());
    }
}

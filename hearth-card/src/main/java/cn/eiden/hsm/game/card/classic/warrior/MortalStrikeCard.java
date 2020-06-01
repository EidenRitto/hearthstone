package cn.eiden.hsm.game.card.classic.warrior;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.expert1.warrior.MortalStrike;
import cn.eiden.hsm.game.minion.Minion;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/21 15:41
 */
@TargetScope
public class MortalStrikeCard extends MortalStrike {
    private static final long BASE_DMG = 4L;
    private static final long EXT_DMG = 6L;
    private static final long HP_CONDITION = 12L;

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        long damage ;
        if (gamer.getHero().getHealth() <= HP_CONDITION){
            damage = EXT_DMG;
        }else {
            damage = BASE_DMG;
        }
        damage = damage + gamer.getGamerSpellDamage();
        target.beHurt(damage);
        target.getOwner().checkMinion();
    }
}

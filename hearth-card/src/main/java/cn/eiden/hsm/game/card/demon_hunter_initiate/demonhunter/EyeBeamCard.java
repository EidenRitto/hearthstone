package cn.eiden.hsm.game.card.demon_hunter_initiate.demonhunter;

import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.demon_hunter_initiate.demonhunter.EyeBeam;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;

/**
 * 眼棱
 * @author Eiden J.P Zhou
 * @date 2020/6/11 14:51
 */
@TargetScope(classScope = MinionObject.class)
public class EyeBeamCard extends EyeBeam {
    public static final int DMG = 3;

    @Override
    public void baseEffect(Gamer gamer, Minion target) {
        gamer.getHero().addLifeSteal();
        target.beHurt(this.getDamage());
        gamer.getHero().removeLifeSteal();
    }

    @Override
    public void outcastEffect(Gamer gamer, Minion target) {
        baseEffect(gamer, target);
    }

    @Override
    public int getCost() {
        return getOwner().isOutcastTrigger(this) ? 1 : super.getCost();
    }

    public int getDamage(){
        return getOwner().getGamerSpellDamage() + DMG;
    }
}

package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.tags.Version;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/18
 *  月火术
 *
 */
@Tags(profession = Profession.Druid,version = Version.BASE)
public class MoonFireCard extends AbstractMagicCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "造成一点伤害。";
    private static final String CARD_NAME = "月火术";
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 1;

    public MoonFireCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Druid);
    }

    @Override
    @TargetScope
    public void magicEffect(Gamer gamer, Minion target){
        int damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        target.beHurt(damage);
    }
}

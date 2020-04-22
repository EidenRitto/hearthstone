package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.objct.hero.HeroObjectAbstract;

import cn.eiden.hsm.game.objct.MinionObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.tags.Stand;


/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *  德鲁伊法术-横扫
 *
 */
@Tags(cardClass = CardClass.DRUID, cardSet = CardSet.CORE)
public class SwipeCard extends AbstractMagicCard {
    private static final int COST = 4;
    private static final String DESCRIPTION = "对一个敌人造成4点伤害，对其他敌人造成1点伤害";
    private static final String CARD_NAME = "横扫";
    /**初始主伤害*/
    private static final int MAGIC_DAMAGE_MAIN = 4;
    /**初始副伤害*/
    private static final int MAGIC_DAMAGE_OTHER = 1;

    public SwipeCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.DRUID);
    }

    @Override
    @TargetScope(stand = Stand.FOE)
    public void magicEffect(Gamer gamer, Minion target){
        int gamerSpellDamage = gamer.getGamerSpellDamage();
        int damageMain = MAGIC_DAMAGE_MAIN + gamerSpellDamage;
        int damageOther = MAGIC_DAMAGE_OTHER + gamerSpellDamage;
        if (target instanceof HeroObjectAbstract){
            ((HeroObjectAbstract)target).beHurt(damageMain);
            gamer.getEnemy().getMinions().forEach(minionObject -> minionObject.beHurt(damageOther));
        }else if (target instanceof MinionObject){
            gamer.getEnemy().getHero().beHurt(damageOther);
            gamer.getEnemy().getMinions().forEach(minionObject -> {
                if (minionObject == target){
                    minionObject.beHurt(damageMain);
                }else {
                    minionObject.beHurt(damageOther);
                }
            });
        }
    }
}

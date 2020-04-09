package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.tags.Ethnicity;
import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.tags.Version;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/11/21
 *  杀戮命令
 *
 */
@Tags(profession = Profession.Hunter,version = Version.BASE)
public class KillCommandCard extends AbstractMagicCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "造成3点伤害。如果你控制野兽，则造成5点伤害";
    private static final String CARD_NAME = "杀戮命令";
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 3;
    /**条件下初始伤害*/
    private static final int MAGIC_DAMAGE_PLUS = 5;

    public KillCommandCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Hunter);
    }

    @Override
    @TargetScope
    public void magicEffect(Gamer gamer, Minion target){
        int damage;
        //检查是否有野兽
        if (gamer.checkHaveEthnicity(Ethnicity.Beast)){
            damage = MAGIC_DAMAGE_PLUS + gamer.getGamerSpellDamage();
        }else {
            damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        }
        if (target instanceof AbstractMinionObject){
           target.beHurt(damage);
        }
//        OutputInfo.info("使用"+CARD_NAME+"对"+((AbstractMinionObject)target).getMinionName()+"造成"+damage+"点伤害");
    }
}

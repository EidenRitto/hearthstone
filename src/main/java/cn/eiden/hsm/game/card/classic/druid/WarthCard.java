package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.annotation.TargetScope;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.MinionObject;
import cn.eiden.hsm.game.tags.Version;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  愤怒
 *
 */
@Tags(profession = Profession.Druid,version = Version.BASE)
public class WarthCard extends AbstractMagicCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "抉择：对一个随从造成3点伤害;或者1点伤害并抽一张牌。";
    private static final String CARD_NAME = "愤怒";
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 3;
    private static final int MAGIC_DAMAGE_DRAW_CARD = 1;
    /**抽卡数量*/
    private static final int DRAW_CARD_NUMBER = 1;

    public WarthCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Hunter);
    }

    @Override
    @TargetScope(classScope = MinionObject.class)
    public void magicEffect(Gamer gamer, Minion target) {
        int damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        int damageDrawCard = MAGIC_DAMAGE_DRAW_CARD + gamer.getGamerSpellDamage();
        if (target instanceof MinionObject){
            MinionObject targetMinion = (MinionObject)target;
            if (gamer.getChooseOne()==0){
                targetMinion.beHurt(damage);
                OutputInfo.info("愤怒选择伤害.");
            }else if (gamer.getChooseOne()==1){
                targetMinion.beHurt(damageDrawCard);
                gamer.drawCard(DRAW_CARD_NUMBER);
                OutputInfo.info("愤怒选择抽牌.");
            }
            gamer.setChooseOne(-1);
        }
    }
}

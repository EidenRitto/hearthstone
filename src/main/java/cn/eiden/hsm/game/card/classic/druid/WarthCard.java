package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.MagicCard;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  愤怒
 *
 */
@Slf4j
public class WarthCard extends MagicCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "抉择：对一个随从造成3点伤害;或者1点伤害并抽一张牌。";
    private static final String CARD_NAME = "愤怒";
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 3;
    private static final int MAGIC_DAMAGE_DRAW_CARD = 1;
    /**抽卡数量*/
    private static final int DRAW_CARD_NUMBER = 1;

    public WarthCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        int damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        int damageDrawCard = MAGIC_DAMAGE_DRAW_CARD + gamer.getGamerSpellDamage();
        if (target instanceof AbstractMinionObject){
            AbstractMinionObject targetMinion = (AbstractMinionObject)target;
            if (gamer.getChooseOne()==0){
                targetMinion.beHurt(damage);
                log.info("愤怒选择伤害.");
            }else if (gamer.getChooseOne()==1){
                targetMinion.beHurt(damageDrawCard);
                gamer.drawCard(DRAW_CARD_NUMBER);
                log.info("愤怒选择抽牌.");
            }
            gamer.setChooseOne(-1);
        }
    }
}

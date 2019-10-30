package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.MagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *  星火术
 *
 */
public class StarFireCard extends MagicCard {
    private static final int COST = 6;
    private static final String DESCRIPTION = "造成5点伤害，抽一张牌。";
    private static final String CARD_NAME = "星火术";
    private static final int MAGIC_DAMAGE = 5;
    private static final int DRAW_CARD_NUMBER = 1;

    public StarFireCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        int damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        if (target instanceof AbstractMinionObject){
            ((AbstractMinionObject)target).beHurt(damage);
        }
        gamer.drawCard(DRAW_CARD_NUMBER);
    }
}

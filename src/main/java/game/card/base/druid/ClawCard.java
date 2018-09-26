package game.card.base.druid;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description: 爪击
 * @Modified By:
 */
public class ClawCard extends MagicCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "在本回合你的英雄获得2点攻击;获得2点护甲。";
    private static final String CARD_NAME = "爪击";
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;
    /**增加的护甲*/
    private static final long ADD_ARMOR = 2;

    public ClawCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        System.out.println("使用法术：爪击.");
        gamer.getHero().addArmor(ADD_ARMOR);
        gamer.getHero().addAttackThisTurn(ADD_ATTACK);
    }
}

package game.card.base.druid;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description: 野蛮咆哮
 * @Modified By:
 */
public class SavageRoarCard extends MagicCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "在本回合你的所有角色获得2点攻击。";
    private static final String CARD_NAME = "野蛮咆哮";
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;

    public SavageRoarCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        System.out.println("使用法术：野蛮咆哮.");
        gamer.getHero().addAttackThisTurn(ADD_ATTACK);
        gamer.getMinions().forEach(minionObject -> minionObject.addAttackThisTurn(ADD_ATTACK));
    }
}

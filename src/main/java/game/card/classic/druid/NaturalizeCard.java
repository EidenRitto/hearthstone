package game.card.classic.druid;

import game.Gamer;
import game.card.MagicCard;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/19
 * @Description: 自然平衡
 * @Modified By:
 */
public class NaturalizeCard extends MagicCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "消灭一个随从，你的对手抽两张牌。";
    private static final String CARD_NAME = "自然平衡";
    private static final int DRAW_CARDS = 2;

    public NaturalizeCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        if (target instanceof MinionObject){
            MinionObject minion = (MinionObject)target;
            if (gamer.getMinions().contains(target)) {
                gamer.deathMinion(gamer.getMinions().indexOf(target));
            }else if (gamer.getEnemy().getMinions().contains(target)){
                gamer.getEnemy().deathMinion(gamer.getEnemy().getMinions().indexOf(target));
            }
        }
        gamer.getEnemy().drawCard(DRAW_CARDS);
    }
}

package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.game.hero.Profession;
import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *  生命之触
 *
 */
public class HealingTouchCard extends AbstractMagicCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "恢复8点生命值。";
    private static final String CARD_NAME = "生命之触";
    private static final long HEALTH_NUMBER = 8;

    public HealingTouchCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Druid);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        gamer.getHero().recovery(HEALTH_NUMBER);
    }
}

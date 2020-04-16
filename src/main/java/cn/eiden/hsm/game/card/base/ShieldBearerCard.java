package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.objct.MinionObject;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.minion.base.ShieldBearer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.tags.Version;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/21
 *  持盾卫士卡牌
 *
 */
@Tags(profession = Profession.Neutral,version = Version.BASE)
public class ShieldBearerCard extends AbstractMinionCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "嘲讽。";
    private static final String CARD_NAME = "持盾卫士";
    private static final Long ATTACK_VALUE = 0L;
    private static final Long HEALTH_LIMIT = 4L;


    public ShieldBearerCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Neutral, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

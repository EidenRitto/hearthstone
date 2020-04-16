package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.objct.MinionObject;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.minion.base.SmallElf;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.tags.Version;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  小精灵卡牌
 * */
@Tags(profession = Profession.Neutral,version = Version.BASE)
public class SmallElfCard extends AbstractMinionCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "小精灵";
    private static final Long ATTACK_VALUE = 1L;
    private static final Long HEALTH_LIMIT = 1L;


    public SmallElfCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Neutral, HEALTH_LIMIT, ATTACK_VALUE);
    }

    @Override
    public MinionObject createMinion() {
        return null;
    }
}

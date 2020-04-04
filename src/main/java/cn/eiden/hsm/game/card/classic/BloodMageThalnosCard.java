package cn.eiden.hsm.game.card.classic;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.minion.classic.BloodMageThalnos;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.tags.Version;


/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/18
 *  血法
 *
 */
@Tags(profession = Profession.Neutral,version = Version.BASE)
public class BloodMageThalnosCard extends AbstractMinionCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "血法师萨尔诺斯";
    private static final Long ATTACK_VALUE = 1L;
    private static final Long HEALTH_LIMIT = 1L;

    public BloodMageThalnosCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Neutral, HEALTH_LIMIT, ATTACK_VALUE,new BloodMageThalnos());
    }

}

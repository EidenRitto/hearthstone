package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.tags.Version;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  爪击
 */
@Tags(profession = Profession.Druid,version = Version.BASE)
public class ClawCard extends AbstractMagicCard {
    private static final int COST = 1;
    private static final String DESCRIPTION = "在本回合你的英雄获得2点攻击;获得2点护甲。";
    private static final String CARD_NAME = "爪击";
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;
    /**增加的护甲*/
    private static final long ADD_ARMOR = 2;

    public ClawCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Druid);
    }

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        OutputInfo.info("使用法术：爪击.");
        gamer.getHero().addArmor(ADD_ARMOR);
        gamer.getHero().addAttackThisTurn(ADD_ATTACK);
    }
}

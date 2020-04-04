package cn.eiden.hsm.game.card.base.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.tags.Version;
import cn.eiden.hsm.output.OutputInfo;

/**
 * 野蛮咆哮
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 */
@Tags(profession = Profession.Druid,version = Version.BASE)
public class SavageRoarCard extends AbstractMagicCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "在本回合你的所有角色获得2点攻击。";
    private static final String CARD_NAME = "野蛮咆哮";
    /**增加的攻击力*/
    private static final long ADD_ATTACK = 2;

    public SavageRoarCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Druid);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target) {
        OutputInfo.info("使用法术：野蛮咆哮.");
        gamer.getHero().addAttackThisTurn(ADD_ATTACK);
        gamer.getMinions().forEach(minionObject -> minionObject.addAttackThisTurn(ADD_ATTACK));
    }
}

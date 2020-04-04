package cn.eiden.hsm.game.card.classic.druid;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.minion.base.derivative.Panther;
import cn.eiden.hsm.game.tags.Version;
import cn.eiden.hsm.output.OutputInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/19
 *  野性之力
 *
 */
@Tags(profession = Profession.Druid,version = Version.BASE)
public class PowerOfTheWildCard extends AbstractMagicCard {
    private static final int COST = 2;
    private static final String DESCRIPTION = "抉择:你的随从获得+1/+1;或者召唤一个3/2猎豹。";
    private static final String CARD_NAME = "野性之力";
    private static final long ADD_HEALTH = 1;
    private static final long ADD_ATTACK = 1;

    public PowerOfTheWildCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Druid);
    }

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        if (gamer.getChooseOne()==0){
            //+1/+1
            gamer.buffYourAllMinions(ADD_ATTACK,ADD_HEALTH);
            OutputInfo.info("野性之力+1/+1.");
        }else if (gamer.getChooseOne()==1){
            //召唤3/2猎豹
            gamer.addMinion(new Panther());
            OutputInfo.info("召唤3/2的黑豹.");
        }
        gamer.setChooseOne(-1);
    }
}

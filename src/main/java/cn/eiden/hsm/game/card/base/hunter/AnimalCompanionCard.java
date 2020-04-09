package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.minion.base.derivative.AnimalCompanionHoff;
import cn.eiden.hsm.game.objct.minion.base.derivative.AnimalCompanionMisa;
import cn.eiden.hsm.game.objct.minion.base.derivative.AnimalCompanionReoque;
import cn.eiden.hsm.game.tags.Version;
import cn.eiden.hsm.output.OutputInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *  动物伙伴
 *
 */
@Tags(profession = Profession.Hunter,version = Version.BASE)
public class AnimalCompanionCard extends AbstractMagicCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "召唤一个随机的动物伙伴。";
    private static final String CARD_NAME = "动物伙伴";

    public AnimalCompanionCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Hunter);
    }

    @Override
    public void magicEffect(Gamer gamer, Minion target){
        if (target==null){
            int randomAnimal = gamer.getRandomSeed().nextInt(3);
            switch (randomAnimal){
                case 0:
                    OutputInfo.info("动物伙伴随机召唤了霍夫");
                    gamer.addMinion(new AnimalCompanionHoff());
                    break;
                case 1:
                    OutputInfo.info("动物伙伴随机召唤了米莎");
                    gamer.addMinion(new AnimalCompanionMisa());
                    break;
                default:
                    OutputInfo.info("动物伙伴随机召唤了雷欧克");
                    gamer.addMinion(new AnimalCompanionReoque());
                    break;
            }
        }
    }
}

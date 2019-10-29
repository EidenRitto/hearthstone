package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.objct.minion.base.derivative.AnimalCompanionHoff;
import cn.eiden.hsm.game.objct.minion.base.derivative.AnimalCompanionMisa;
import cn.eiden.hsm.game.objct.minion.base.derivative.AnimalCompanionReoque;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.MagicCard;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 * @Description: 动物伙伴
 * @Modified By:
 */
public class AnimalCompanionCard extends MagicCard{
    private static final int COST = 1;
    private static final String DESCRIPTION = "召唤一个随机的动物伙伴。";
    private static final String CARD_NAME = "动物伙伴";

    public AnimalCompanionCard() {
        super(COST, DESCRIPTION, CARD_NAME);
    }

    @Override
    public void magicEffect(Gamer gamer, GameObject target){
        if (target==null){
            int randomAnimal = gamer.getRandomSeed().nextInt(3);
            switch (randomAnimal){
                case 0:
                    System.out.println("动物伙伴随机召唤了霍夫");
                    gamer.addMinion(new AnimalCompanionHoff());
                    break;
                case 1:
                    System.out.println("动物伙伴随机召唤了米莎");
                    gamer.addMinion(new AnimalCompanionMisa());
                    break;
                default:
                    System.out.println("动物伙伴随机召唤了雷欧克");
                    gamer.addMinion(new AnimalCompanionReoque());
                    break;
            }
        }
    }
}

package cn.eiden.hsm.game.card.base.hunter;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.game.minion.Minion;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;


import java.util.Random;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/11/21
 *  多重射击
 *
 */
@Tags(cardClass = CardClass.HUNTER, cardSet = CardSet.CORE)
public class MultiShotCard extends AbstractMagicCard {
    private static final int COST = 4;
    private static final String DESCRIPTION = "对2个随机敌方随从造成3点伤害";
    private static final String CARD_NAME = "多重射击";
    private static final int TARGET_NUMBER = 2;
    /**初始伤害*/
    private static final int MAGIC_DAMAGE = 3;

    public MultiShotCard() {
        super(COST, DESCRIPTION, CARD_NAME, CardClass.HUNTER);
    }

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        int index = gamer.getEnemy().getMinions().size();
        if (index < TARGET_NUMBER){
            return;
        }
        Random random = gamer.getRandomSeed();
        int damage = MAGIC_DAMAGE + gamer.getGamerSpellDamage();
        int randomSeed = random.nextInt(index);
        int randomSeed2 = random.nextInt(index);
        while (randomSeed == randomSeed2){
            randomSeed2 = random.nextInt(index);
        }
        gamer.getEnemy().getMinion(randomSeed).beHurt(damage);
        gamer.getEnemy().getMinion(randomSeed2).beHurt(damage);
    }
}
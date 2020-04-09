package cn.eiden.hsm.game.card.base;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMagicCard;
import cn.eiden.hsm.game.tags.Version;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *
 * */
@Tags(profession = Profession.Neutral,version = Version.BASE)
public class CoinCard extends AbstractMagicCard {
    private static final int COST = 0;
    private static final String DESCRIPTION = "获得一个法力水晶。";
    private static final String CARD_NAME = "幸运币";
    private static final int MAGIC_CRYSTAL = 1;

    public CoinCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Neutral);
    }

    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.getManaCrystal().addAlive(MAGIC_CRYSTAL);
    }
}

package cn.eiden.hsm.game.card;


import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.GameObject;
import cn.eiden.hsm.game.Gamer;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *
 * */
public abstract class AbstractMagicCard extends AbstractCard {

    public abstract void magicEffect(Gamer gamer, Minion target);

    public AbstractMagicCard(int cost, String description, String cardName, Profession profession) {
        super(cost, description, cardName ,profession);
    }
}

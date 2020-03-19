package cn.eiden.hsm.game.card;


import cn.eiden.hsm.game.objct.GameObject;
import cn.eiden.hsm.game.Gamer;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *
 * */
public class MagicCard extends AbstractCard {
    @Override
    public void usingCard() {

    }

    public void magicEffect(Gamer gamer, GameObject target){

    }

    public MagicCard(int cost, String description,String cardName) {
        super(cost, description, cardName);
    }
}

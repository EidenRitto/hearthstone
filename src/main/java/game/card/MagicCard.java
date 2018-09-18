package game.card;


import game.Gamer;
import game.objct.GameObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description :
 * */
public class MagicCard extends AbstractCard {
    @Override
    public void useCard() {

    }

    public void magicEffect(Gamer gamer,GameObject target){

    }

    public MagicCard(int cost, String description,String cardName) {
        super(cost, description, cardName);
    }
}

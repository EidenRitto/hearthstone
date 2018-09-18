package game.objct;


import game.card.AbstractCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description :
 * */
public class GamerboardObject {
    List<ServantObject> ourServantPlace;
    List<ServantObject> yourServantPlace;

    List<AbstractCard> ourHandsCard;
    List<AbstractCard> yourHandsCard;

    List<AbstractCard> ourCards;
    List<AbstractCard> yourCards;

    public GamerboardObject() {
        ourServantPlace = new ArrayList<>(6);
        yourServantPlace = new ArrayList<>(6);
    }
}

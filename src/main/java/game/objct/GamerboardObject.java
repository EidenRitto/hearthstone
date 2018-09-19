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
    List<MinionObject> ourMinionPlace;
    List<MinionObject> yourMinionPlace;

    List<AbstractCard> ourHandsCard;
    List<AbstractCard> yourHandsCard;

    List<AbstractCard> ourCards;
    List<AbstractCard> yourCards;

    public GamerboardObject() {
        ourMinionPlace = new ArrayList<>(6);
        yourMinionPlace = new ArrayList<>(6);
    }
}

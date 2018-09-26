package game.card.base.warrior;

import game.card.WeaponCard;
import game.objct.weapon.FieryWarAxe;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 * @Description: 炙炎战斧
 * @Modified By:
 */
public class FieryWarAxeCard extends WeaponCard{
    private static final int COST = 3;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "炙炎战斧";
    private static final Long ATTACK_VALUE = 3L;
    private static final Long DURABLE = 2L;


    public FieryWarAxeCard() {
        super(COST, DESCRIPTION, CARD_NAME, DURABLE, ATTACK_VALUE,new FieryWarAxe());
    }
}

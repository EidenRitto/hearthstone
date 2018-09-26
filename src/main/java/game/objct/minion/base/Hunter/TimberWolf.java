package game.objct.minion.base.Hunter;

import game.Gamer;
import game.objct.Ethnicity;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @Date: 2018/9/22
 * @Description: 森林狼
 * @Modified By:
 */
public class TimberWolf extends MinionObject {
    private static final String SERVANT_NAME = "森林狼";
    private static final Long ATTACK = 1L;
    private static final Long HEALTH = 1L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    public TimberWolf() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        ethnicity = Ethnicity.Beast;
    }


}

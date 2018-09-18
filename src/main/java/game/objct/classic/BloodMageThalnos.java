package game.objct.classic;

import game.Gamer;
import game.objct.ServantObject;

/**
 * @author: Eiden J.P Zhou
 * @Date: 2018/9/18
 * @Description:
 * @Modified By:
 */
public class BloodMageThalnos extends ServantObject {
    private static final String SERVANT_NAME = "血法师萨尔诺斯";
    private static final Long HEALTH = 1L;
    private static final Long ATTACK = 1L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    private static final int DRAW_CARD_NUMBER = 1;

    public BloodMageThalnos() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
    }

    @Override
    public void doDeathRattle(Gamer self){
        self.drawCard(DRAW_CARD_NUMBER);
    }
}

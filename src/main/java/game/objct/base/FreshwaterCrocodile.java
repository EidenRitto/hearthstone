package game.objct.base;

import game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description : 淡水鳄 223
 * */
public class FreshwaterCrocodile extends MinionObject {
    private static final String SERVANT_NAME = "淡水鳄";
    private static final Long ATTACK = 2L;
    private static final Long HEALTH = 3L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    public FreshwaterCrocodile() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
    }
}

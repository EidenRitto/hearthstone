package game.objct.minion.base;


import game.objct.MinionObject;


/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description : 小精灵 011
 * */
public class SmallElf extends MinionObject{
    private static final String SERVANT_NAME = "小精灵";
    private static final Long HEALTH = 1L;
    private static final Long ATTACK = 1L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    public SmallElf() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
    }

}

package cn.eiden.hsm.game.minion.minion.base;


import cn.eiden.hsm.game.minion.MinionObject;


/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  小精灵 011
 * */
public class SmallElf extends MinionObject {
    private static final String SERVANT_NAME = "小精灵";
    private static final Long HEALTH = 1L;
    private static final Long ATTACK = 1L;

    public SmallElf() {
        super(SERVANT_NAME,HEALTH, ATTACK);
    }

}

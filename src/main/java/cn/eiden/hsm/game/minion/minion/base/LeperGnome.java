package cn.eiden.hsm.game.minion.minion.base;

import cn.eiden.hsm.game.minion.MinionObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  麻风侏儒 111
 * */
@Slf4j
public class LeperGnome extends MinionObject{
    private static final String SERVANT_NAME = "麻风侏儒";
    private static final Long HEALTH = 1L;
    private static final Long ATTACK = 1L;

    public LeperGnome() {
        super(SERVANT_NAME,HEALTH, ATTACK);
    }
}

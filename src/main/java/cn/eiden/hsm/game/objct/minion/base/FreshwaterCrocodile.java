package cn.eiden.hsm.game.objct.minion.base;

import cn.eiden.hsm.game.Ethnicity;
import cn.eiden.hsm.game.objct.AbstractMinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  淡水鳄 223
 * */
public class FreshwaterCrocodile extends AbstractMinionObject {
    private static final String SERVANT_NAME = "淡水鳄";
    private static final Long ATTACK = 2L;
    private static final Long HEALTH = 3L;

    public FreshwaterCrocodile() {
        super(SERVANT_NAME,HEALTH, ATTACK);
        ethnicity = Ethnicity.Beast;
    }
}

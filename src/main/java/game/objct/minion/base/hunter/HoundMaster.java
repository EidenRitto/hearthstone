package game.objct.minion.base.hunter;

import game.objct.Ethnicity;
import game.objct.GameObject;
import game.objct.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/11/21
 * @Description: 驯兽师
 * @Modified By:
 */
public class HoundMaster extends MinionObject {
    private static final String SERVANT_NAME = "驯兽师";
    private static final Long ATTACK = 4L;
    private static final Long HEALTH = 3L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 0;

    private static final Long ADD_ATTACK = 2L;
    private static final Long ADD_HEALTH = 2L;

    public HoundMaster() {
        super(SERVANT_NAME, HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
    }

    @Override
    public void doBattle(GameObject target){
        MinionObject minion = (MinionObject)target;
        if (minion.getEthnicity() != Ethnicity.Beast){
            return;
        }
        minion.addAttack(ADD_ATTACK);
        minion.addHealthLimit(ADD_HEALTH);
        minion.addTaunt();
        System.out.println("打出卡牌"+SERVANT_NAME+",目标"+minion.getMinionName()+"+2/+2和嘲讽");
    }
}

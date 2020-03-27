package cn.eiden.hsm.game.objct.minion.base.hunter;

import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.Ethnicity;
import cn.eiden.hsm.game.GameObject;
import lombok.extern.slf4j.Slf4j;

/**
 * 驯兽师
 * @author : Eiden J.P Zhou
 * @date : 2018/11/21
 */
@Slf4j
public class HoundMaster extends AbstractMinionObject implements Battle {
    private static final String SERVANT_NAME = "驯兽师";
    private static final Long ATTACK = 4L;
    private static final Long HEALTH = 3L;

    private static final Long ADD_ATTACK = 2L;
    private static final Long ADD_HEALTH = 2L;

    public HoundMaster() {
        super(SERVANT_NAME, HEALTH, ATTACK);
    }

    @Override
    public void doBattle(GameObject target){
        AbstractMinionObject minion = (AbstractMinionObject)target;
        if (minion.getEthnicity() != Ethnicity.Beast){
            return;
        }
        minion.addAttack(ADD_ATTACK);
        minion.addHealthLimit(ADD_HEALTH);
        minion.addTaunt();
        log.info("打出卡牌"+SERVANT_NAME+",目标"+minion.getMinionName()+"+2/+2和嘲讽");
    }
}

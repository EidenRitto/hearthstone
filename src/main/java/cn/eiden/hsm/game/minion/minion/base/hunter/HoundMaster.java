package cn.eiden.hsm.game.minion.minion.base.hunter;

import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.keyword.Battle;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.output.OutputInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 驯兽师
 * @author : Eiden J.P Zhou
 * @date : 2018/11/21
 */
@Slf4j
public class HoundMaster extends MinionObject implements Battle {
    private static final String SERVANT_NAME = "驯兽师";
    private static final Long ATTACK = 4L;
    private static final Long HEALTH = 3L;

    private static final Long ADD_ATTACK = 2L;
    private static final Long ADD_HEALTH = 2L;

    public HoundMaster() {
        super(SERVANT_NAME, HEALTH, ATTACK);
    }

    @Override
    public void doBattle(Minion minion){
        if (minion.getRace() != Race.BEAST){
            return;
        }
        minion.addAttack(ADD_ATTACK);
        minion.addHealthLimit(ADD_HEALTH);
        minion.addTaunt();
        OutputInfo.info("打出卡牌"+SERVANT_NAME+",目标"+minion.getMinionName()+"+2/+2和嘲讽");
    }
}
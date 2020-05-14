package cn.eiden.hsm.game.minion.minion.base.hunter;

import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.keyword.BuffHole;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.game.minion.Minion;

import java.util.List;

/**
 * 森林狼
 * @author : Eiden J.P Zhou
 * @date 2018/9/22
 *
 *
 */
public class TimberWolf extends MinionObject implements BuffHole {
    private static final String SERVANT_NAME = "森林狼";
    private static final Long ATTACK = 1L;
    private static final Long HEALTH = 1L;

    private static final long BUFF_ATTACK = 1;
    private static final long BUFF_HEALTH = 0;
    private static final Race BUFF_PREREQUISITE = Race.BEAST;

    public TimberWolf() {
        super(SERVANT_NAME,HEALTH, ATTACK);
    }


    @Override
    public void doBuffHole() {
        List<Minion> minions = this.getOwner().getMinions();
        for (Minion minion : minions) {
            if (minion.getRace() == BUFF_PREREQUISITE && !minion.equals(this) ){
                minion.addAttack(BUFF_ATTACK);
            }
        }
    }
}

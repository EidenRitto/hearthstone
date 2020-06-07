package cn.eiden.hsm.game.minion;

import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.output.OutputInfo;

/**
 * 随从（真）类
 * @author Eiden J.P Zhou
 * @date 2020/4/4 15:44
 */
public class MinionObject extends AbstractMinion {

    public MinionObject() {
    }

    public MinionObject(String minionName, Long healthLimit, Long attackValue, Race race, String cardId) {
        super(minionName, healthLimit, attackValue,race,cardId);
    }

    @Override
    public void reduceHealth(Minion source, long reduceHealth) {
        super.reduceHealth(source, reduceHealth);
        if (source.hasPoisonous()){
            OutputInfo.info("%s具有剧毒,%s直接死亡",source.getMinionName(),this.getMinionName());
            this.setDead();
        }
    }
}

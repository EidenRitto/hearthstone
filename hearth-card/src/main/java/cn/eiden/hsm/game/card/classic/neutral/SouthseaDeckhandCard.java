package cn.eiden.hsm.game.card.classic.neutral;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.BattlefieldChangeEvent;
import cn.eiden.hsm.game.card.defs.expert1.neutral.SouthseaDeckhand;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;
import cn.eiden.hsm.listener.MinionListener;
import cn.eiden.hsm.output.OutputInfo;

/**
 * 南海船工
 * @author Eiden J.P Zhou
 * @date 2020/5/21 15:25
 */
public class SouthseaDeckhandCard extends SouthseaDeckhand {

    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new RushListener());
        return minion;
    }

    static class RushListener extends AbstractMinionListener implements MinionListener {
        @EventHandler
        public void onEvent(BattlefieldChangeEvent event){
            if (getMinion().getOwner().getHero().hasWeapon()){
                if (!getMinion().hasCharge()){
                    getMinion().addCharge();
                    OutputInfo.info("%s获得冲锋",getMinion().getMinionName());
                }
            }else {
                if (getMinion().hasCharge()){
                    getMinion().removeCharge();
                    OutputInfo.info("%s失去冲锋",getMinion().getMinionName());
                }
            }
        }
    }
}

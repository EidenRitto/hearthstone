package cn.eiden.hsm.game.card.classic;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.BattlefieldChangeEvent;
import cn.eiden.hsm.game.card.defs.expert1.neutral.SouthseaDeckhand;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;
import cn.eiden.hsm.output.OutputInfo;
import lombok.Setter;

/**
 * 南海船工
 * @author Eiden J.P Zhou
 * @date 2020/5/21 15:25
 */
public class SouthseaDeckhandCard extends SouthseaDeckhand {

    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setHearthListener(new RushListener(minion));
        return minion;
    }

    static class RushListener implements HearthListener{
        @Setter
        private Minion minion;
        @EventHandler
        public void onEvent(BattlefieldChangeEvent event){
            if (minion.getOwner().getHero().hasWeapon()){
                if (!minion.hasCharge()){
                    minion.addCharge();
                    OutputInfo.info("%s获得冲锋",minion.getMinionName());
                }
            }else {
                if (minion.hasCharge()){
                    minion.removeCharge();
                    OutputInfo.info("%s失去冲锋",minion.getMinionName());
                }
            }
        }

        public RushListener(Minion minion) {
            this.minion = minion;
        }
    }
}

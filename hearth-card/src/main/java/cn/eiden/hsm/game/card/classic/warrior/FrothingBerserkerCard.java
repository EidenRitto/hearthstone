package cn.eiden.hsm.game.card.classic.warrior;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.MinionBeHurtEvent;
import cn.eiden.hsm.event.events.UseCardFromHandEvent;
import cn.eiden.hsm.game.card.defs.expert1.warrior.FrothingBerserker;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.listener.AbstractMinionListener;
import cn.eiden.hsm.listener.MinionListener;

/**
 * demo</br>
 * 暴乱狂战士
 * @author Eiden J.P Zhou
 * @date 2020/5/21 11:35
 */
public class FrothingBerserkerCard extends FrothingBerserker {

    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        EffectListener effectListener = new EffectListener();
        effectListener.setMinion(minion);
        minion.setMinionListener(effectListener);
        return minion;
    }

    static class EffectListener extends AbstractMinionListener implements MinionListener {
        @EventHandler
        public void onEvent(MinionBeHurtEvent event){
            getMinion().addAttack(1L);
        }
    }
}

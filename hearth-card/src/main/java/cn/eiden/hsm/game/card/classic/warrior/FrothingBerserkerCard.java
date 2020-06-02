package cn.eiden.hsm.game.card.classic.warrior;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.MinionBeHurtEvent;
import cn.eiden.hsm.game.card.defs.expert1.warrior.FrothingBerserker;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.hero.HeroMinion;
import cn.eiden.hsm.listener.HearthListener;
import lombok.Setter;

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
        effectListener.setFrothingBerserker(minion);
        minion.setHearthListener(effectListener);
        return minion;
    }

    static class EffectListener implements HearthListener{
        @Setter
        private Minion frothingBerserker;

        @EventHandler
        public void onEvent(MinionBeHurtEvent event){
            if (event.getMinion() instanceof HeroMinion){
                return;
            }
            frothingBerserker.addAttack(1L);
        }
    }
}

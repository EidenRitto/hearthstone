package cn.eiden.hsm.game.card.uldum.mage;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseSecretCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.uldum.mage.ArcaneFlakmage;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;

import java.util.List;

/**
 * 对空奥术法师
 *
 * @author Eiden J.P Zhou
 * @date 2020/6/1 14:07
 */
public class ArcaneFlakmageCard extends ArcaneFlakmage {
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setHearthListener(new FlakListener(minion));
        return minion;
    }

    static class FlakListener implements HearthListener {
        private Minion thisMinion;

        @EventHandler
        public void onEvent(UseSecretCardFromHandEvent event) {
            Gamer enemy = event.getOwner().getEnemy();
            List<Minion> minions = enemy.getMinions();
            minions.forEach(e -> e.beHurt(thisMinion, 2));
            enemy.checkMinion();
        }

        public FlakListener(Minion thisMinion) {
            this.thisMinion = thisMinion;
        }
    }
}

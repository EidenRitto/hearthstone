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
 * @author Eiden J.P Zhou
 * @date 2020/6/1 14:07
 */
public class ArcaneFlakmageCard extends ArcaneFlakmage {
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setHearthListener(new FlakListener());
        return minion;
    }

    static class FlakListener implements HearthListener{
        @EventHandler
        public void onEvent(UseSecretCardFromHandEvent event){
            Gamer enemy = event.getOwner().getEnemy();
            List<Minion> minions = enemy.getMinions();
            minions.forEach(e->e.beHurt(2));
            enemy.checkMinion();
        }
    }
}

package cn.eiden.hsm.game.card.demon_hunter_initiate.demonhunter;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.demon_hunter_initiate.demonhunter.AltruisTheOutcast;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;
import cn.eiden.hsm.output.OutputInfo;

/**
 * 流放者奥图里斯
 * @author Eiden J.P Zhou
 * @date 2020/6/12 17:03
 */
public class AltruisTheOutcastCard extends AltruisTheOutcast {

    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new AltruisListener());
        return minion;
    }

    private static class AltruisListener extends AbstractMinionListener{
        @EventHandler
        public void onEvent(UseCardFromHandEvent event){
            Minion minion = getMinion();
            if (event.getOwner() == minion.getOwner()
                    &&event.getOwner().isOutcastTrigger(event.getCard())){
                OutputInfo.info("%s特效触发",minion.getMinionName());
                Gamer enemy = minion.getOwner().getEnemy();
                enemy.getHero().beHurt(minion,1);
                enemy.getMinions().forEach(e->e.beHurt(minion,1));
                enemy.checkMinion();
            }
        }
    }
}

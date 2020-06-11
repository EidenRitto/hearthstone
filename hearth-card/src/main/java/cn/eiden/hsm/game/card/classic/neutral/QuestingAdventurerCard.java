package cn.eiden.hsm.game.card.classic.neutral;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseCardFromHandEvent;
import cn.eiden.hsm.game.card.defs.expert1.neutral.QuestingAdventurer;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;

/**
 * 任务达人
 * @author Eiden J.P Zhou
 * @date 2020/6/11 17:17
 */
public class QuestingAdventurerCard extends QuestingAdventurer {
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new Listener());
        return minion;
    }

    static class Listener extends AbstractMinionListener{
        @EventHandler
        public void onEvent(UseCardFromHandEvent event){
            if (event.getOwner() == getMinion().getOwner()){
                getMinion().addHealthLimit(1);
                getMinion().addAttack(1);
            }
        }
    }
}

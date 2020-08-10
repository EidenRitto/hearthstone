package cn.eiden.hsm.game.card.sch.neutral;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseSpellCardFromHandEvent;
import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.EducatedElekk;
import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 驯化的雷象
 * @author Eiden J.P Zhou
 * @date 2020/8/10 11:35
 */
public class EducatedElekkCard extends EducatedElekk {

    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new MyListener());
        return minion;
    }

    @Override
    protected DeathRattle selfDeathRattle() {
        return new MyDeathRattle();
    }

    /**
     * 亡语：</b>将记住的法术牌洗入你的牌库。
     */
    public static class MyDeathRattle implements DeathRattle{
        @Override
        public void doDeathRattle(GeneralItem generalItem) {
            if (generalItem instanceof Minion){
                Minion minion = (Minion) generalItem;
                MyListener myListener = (MyListener) minion.getMinionListener();
                List<Card> cardMemory = myListener.getCardMemory();
                cardMemory.forEach(e->minion.getOwner().shuffleCard(e));
            }
        }
    }

    /**
     * 每当使用一张法术牌，该随从会记住法术。
     */
    public static class MyListener extends AbstractMinionListener{
        private List<Card> cardMemory = new ArrayList<>();
        @EventHandler
        public void onEvent(UseSpellCardFromHandEvent event){
            if (event.getOwner() == getMinion().getOwner()){
                cardMemory.add(event.getMagicCard());
            }
        }

        public List<Card> getCardMemory() {
            return cardMemory;
        }
    }
}

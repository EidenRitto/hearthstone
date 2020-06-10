package cn.eiden.hsm.game.card.ungoro.paladin;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseSpellCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.ungoro.paladin.TheLastKaleidosaur;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.game.quest.AbstractQuest;
import cn.eiden.hsm.game.quest.Quest;
import cn.eiden.hsm.game.quest.Reward;
import cn.eiden.hsm.listener.AbstractQuestListener;

/**
 * 最后的水晶龙
 * @author Eiden J.P Zhou
 * @date 2020/6/10 11:47
 */
public class TheLastKaleidosaurCard extends TheLastKaleidosaur {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.addQuest(new ThisQuest());
    }

    private static class ThisQuest extends AbstractQuest{

        @Override
        public Reward getReward() {
            return (o) -> o.getHand().addHandsCard(CardFactory.getCardById(41946));
        }

        public ThisQuest() {
            super(6, new QuestListener());
        }
    }

    private static class QuestListener extends AbstractQuestListener {
        @EventHandler
        public void onEvent(UseSpellCardFromHandEvent event){
            Minion target = event.getTarget();
            Gamer owner = this.getQuest().getOwner();
            if (event.getOwner() == owner
                    && target instanceof MinionObject
                    && owner.isFriend(target)){
                this.getQuest().addProgress(1);
                if (this.getQuest().checkComplete()){
                    this.getQuest().getReward().earnRewards(owner);
                    owner.removeQuest(getQuest());
                }
            }
        }
    }
}

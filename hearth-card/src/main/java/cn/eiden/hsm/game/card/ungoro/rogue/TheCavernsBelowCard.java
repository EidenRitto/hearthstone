package cn.eiden.hsm.game.card.ungoro.rogue;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.ungoro.rogue.TheCavernsBelow;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.quest.AbstractQuest;
import cn.eiden.hsm.game.quest.Quest;
import cn.eiden.hsm.game.quest.Reward;
import cn.eiden.hsm.listener.AbstractQuestListener;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/10 14:14
 */
public class TheCavernsBelowCard extends TheCavernsBelow {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.addQuest(new ThisQuest());
    }

    private static class ThisQuest extends AbstractQuest {

        @Override
        public Reward getReward() {
            return (o) -> o.getHand().addHandsCard(CardFactory.getCardById(QUEST_REWARD_DATABASE_ID));
        }

        public ThisQuest() {
            super(QUEST_PROGRESS_TOTAL, new QuestListener());
        }
    }

    private static class QuestListener extends AbstractQuestListener{
        @EventHandler
        public void onEvent(UseMinionCardFromHandEvent event){
            Quest quest = this.getQuest();
            Gamer owner = quest.getOwner();
            Minion minion = event.getMinionObject();
            if (owner == event.getOwner()){
                int usedNum = owner.getHistory().getCountByMinionName(minion);
                quest.setProgress(usedNum+1);
                if (quest.checkComplete()){
                    quest.getReward().earnRewards(owner);
                    owner.removeQuest(quest);
                }
            }
        }
    }
}

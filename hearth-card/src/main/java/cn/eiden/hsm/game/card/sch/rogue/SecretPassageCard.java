package cn.eiden.hsm.game.card.sch.rogue;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.StartTurnEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.scholomance.rogue.SecretPassage;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;
import cn.eiden.hsm.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 秘密通道
 * @author Eiden J.P Zhou
 * @date 2020/8/7 11:41
 */
public class SecretPassageCard extends SecretPassage {

    private static final int CARD_NUM = 5;

    /**
     * "将你的手牌替换为你牌库中的5张牌。下回合换回。"
     * @param gamer 当前卡牌所有者
     * @param target 所指定目标
     */
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        List<Card> secretPassage = new ArrayList<>();
        for (int i = 0; i < CARD_NUM; i++) {
            Card randomOne = RandomUtils.getRandomOne(gamer.getDeckCards());
            secretPassage.add(randomOne);
        }
        List<Card> tempCards = gamer.getHand().getCards();
        gamer.getHand().setCards(secretPassage);
        gamer.getEventManager().registerListener(new SecretPassageListener(gamer, tempCards));
    }

    public static class SecretPassageListener implements HearthListener{
        private Gamer owner;
        private List<Card> secretPassage;

        @EventHandler
        public void onEvent(StartTurnEvent startTurnEvent){
            if (this.owner == startTurnEvent.getOwner()){
                List<Card> tempCards = this.owner.getHand().getCards();
                tempCards.forEach(e->this.owner.shuffleCard(e));
                this.owner.getHand().setCards(secretPassage);
                //注销该事件
                this.owner.getEventManager().removeListener(this);
            }
        }

        public SecretPassageListener(Gamer owner, List<Card> secretPassage) {
            this.owner = owner;
            this.secretPassage = secretPassage;
        }
    }
}

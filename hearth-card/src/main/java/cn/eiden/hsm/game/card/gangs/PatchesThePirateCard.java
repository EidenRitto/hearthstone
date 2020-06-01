package cn.eiden.hsm.game.card.gangs;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.event.EventManager;
import cn.eiden.hsm.event.events.AddMinionEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.gangs.neutral.PatchesThePirate;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/22 9:55
 */
public class PatchesThePirateCard extends PatchesThePirate {

    public PatchesThePirateCard() {
        super();
        //非全局监听手动注册,只有存在帕奇斯这张牌时才加入观察者模式
        EventManager.getInstance().registerListener(new PatchesListener());
    }
    /**在你使用一张海盗牌后，从你的牌库中将该随从置入战场。*/
    static class PatchesListener implements HearthListener{
        @EventHandler
        public void onEvent(AddMinionEvent event){
            Gamer owner = event.getOwner();
            Minion minion = event.getMinion();
            //判断使用的是不是海盗
            if (minion.getRace() == Race.PIRATE){
                //获取牌库的所有牌
                List<Card> cards = owner.getDeckCards();
                //拿到所有的帕奇斯
                List<Card> patches = cards.stream()
                        .filter(e -> e instanceof PatchesThePirateCard)
                        .collect(Collectors.toList());
                //所有帕奇斯进入战场
                for (Card card : patches) {
                    //必须先从卡组中移除，否则会无限套娃！
                    owner.deckLoss(card);
                    AbstractMinionCard patchesCard = (AbstractMinionCard) card;
                    owner.addMinion(patchesCard.createMinion());
                }
            }
        }
    }
}

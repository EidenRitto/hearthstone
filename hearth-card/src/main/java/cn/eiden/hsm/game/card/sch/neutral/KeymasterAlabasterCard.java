package cn.eiden.hsm.game.card.sch.neutral;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.DrawCardEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.Card;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.KeymasterAlabaster;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;

/**
 * 钥匙专家阿拉巴斯特
 * @author Eiden J.P Zhou
 * @date 2020/8/10 10:05
 */
public class KeymasterAlabasterCard extends KeymasterAlabaster {
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new MyListener());
        return minion;
    }

    /**
     * "每当你的对手抽一张牌时，将一张复制置入你的手牌，其法力值消耗变为（1）点。"
     */
    public static class MyListener extends AbstractMinionListener{
        @EventHandler
        public void onEvent(DrawCardEvent drawCardEvent){
            Gamer minionMaster = getMinion().getOwner();
            if (minionMaster.getEnemy() == drawCardEvent.getOwner()){
                Card clone = drawCardEvent.getCard().clone();
                clone.setCost(1);
                minionMaster.getHand().addHandsCard(clone);
            }
        }
    }
}

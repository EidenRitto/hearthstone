package cn.eiden.hsm.game.card.sch.warrior;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.EndTurnEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.warrior.Troublemaker;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;
import cn.eiden.hsm.listener.MinionListener;
import cn.eiden.hsm.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 问题学生
 * @author Eiden J.P Zhou
 * @date 2020/8/7 16:35
 */
public class TroublemakerCard extends Troublemaker {
    /**
     * "在你的回合结束时，召唤两个3/3的无赖并使其攻击随机敌人。"
     */
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new AfterTurnListener());
        return minion;
    }

    public static class AfterTurnListener extends AbstractMinionListener implements MinionListener {
        @EventHandler
        public void onEvent(EndTurnEvent endTurnEvent){
            Gamer eventOwner = endTurnEvent.getOwner();
            if (eventOwner == getMinion().getOwner()){
                //无赖
                AbstractMinionCard refCard = (AbstractMinionCard) CardFactory.getCardById(59949);
                assert refCard != null;
                Minion minion = refCard.createMinion();
                Minion minion2 = refCard.createMinion();
                List<Minion> allEnemy = new ArrayList<>(eventOwner.getEnemy().getAllMinion());
                if (eventOwner.addMinion(minion)){
                    Minion randomOne = RandomUtils.getRandomOne(allEnemy);
                    minion.attack(randomOne);
                }
                if (eventOwner.addMinion(minion2)){
                    allEnemy.removeIf(e->e.getHealth()>0);
                    Minion randomTwo = RandomUtils.getRandomOne(allEnemy);
                    minion2.attack(randomTwo);
                }
                eventOwner.getEnemy().checkMinion();
                eventOwner.getEnemy().checkHero();
            }
        }
    }
}

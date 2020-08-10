package cn.eiden.hsm.game.card.sch.neutral;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseSpellCardFromHandEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractMinionCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.scholomance.neutral.Ogremancer;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.AbstractMinionListener;

import java.util.Objects;

/**
 * 食人魔巫术师
 * @author Eiden J.P Zhou
 * @date 2020/8/10 14:31
 */
public class OgremancerCard extends Ogremancer {
    /**
     * "每当你的对手施放一个法术，召唤一个2/2并具有<b>嘲讽</b>的骷髅。"
     */
    @Override
    public Minion createMinion() {
        Minion minion = super.createMinion();
        minion.setMinionListener(new MyListener());
        return minion;
    }

    public static class MyListener extends AbstractMinionListener{
        @EventHandler
        public void onEvent(UseSpellCardFromHandEvent event){
            Gamer owner = getMinion().getOwner();
            if (event.getOwner() == owner.getEnemy()){
                AbstractMinionCard refCard = (AbstractMinionCard) CardFactory.getCardById(59919);
                owner.addMinion(Objects.requireNonNull(refCard).createMinion());
            }
        }
    }

}

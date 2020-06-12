package cn.eiden.hsm.game.card.blacktemple.demonhunter;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.HeroPowerBeUsedEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.AbstractHeroPowerCard;
import cn.eiden.hsm.game.card.CardFactory;
import cn.eiden.hsm.game.card.defs.black_temple.demonhunter.Metamorphosis;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;
import cn.eiden.hsm.output.OutputInfo;

/**
 * 恶魔变形
 * @author Eiden J.P Zhou
 * @date 2020/6/12 17:18
 */
public class MetamorphosisCard extends Metamorphosis {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        AbstractHeroPowerCard oldPower = gamer.getHero().getHeroPower();
        AbstractHeroPowerCard newPower = (AbstractHeroPowerCard) CardFactory.getCardById(56877);
        gamer.getHero().setHeroPower(newPower);
        gamer.getEventManager().removeListener(new UseTwoChangePower(oldPower, newPower, gamer));
    }

    private static class UseTwoChangePower implements HearthListener {
        private AbstractHeroPowerCard oldPower;
        private AbstractHeroPowerCard newPower;
        private Gamer gamer;
        private int useTimes = 0;

        @EventHandler
        public void onEvent(HeroPowerBeUsedEvent event) {
            if (event.getHeroPowerCard() == newPower){
                useTimes++;
                if (useTimes>1){
                    OutputInfo.info("%s使用完次数，技能变回%s",newPower.getCardName(),oldPower.getCardName());
                    gamer.getHero().setHeroPower(oldPower);
                    gamer.getEventManager().removeListener(this);
                }
            }
        }

        public UseTwoChangePower(AbstractHeroPowerCard oldPower, AbstractHeroPowerCard newPower, Gamer gamer) {
            this.oldPower = oldPower;
            this.newPower = newPower;
            this.gamer = gamer;
        }
    }
}

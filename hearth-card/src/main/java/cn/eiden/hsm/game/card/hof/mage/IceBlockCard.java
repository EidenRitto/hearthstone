package cn.eiden.hsm.game.card.hof.mage;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.MinionBeHurtEvent;
import cn.eiden.hsm.game.card.defs.hof.mage.IceBlock;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Secret;
import cn.eiden.hsm.game.minion.hero.HeroMinion;

/**
 * 寒冰屏障
 * @author Eiden J.P Zhou
 * @date 2020/6/2 14:26
 */
public class IceBlockCard extends IceBlock {
    @Override
    public Secret createSecret() {
        return new IceBlockSecret();
    }

    static class IceBlockSecret extends AbstractSecret{

        @Override
        public boolean onSecret(Event event) {
            if (event.getClass() == triggerEvent()){
                MinionBeHurtEvent minionBeHurtEvent = (MinionBeHurtEvent) event;
                Minion minion = minionBeHurtEvent.getMinion();
                long dmg = minionBeHurtEvent.getDmg();
                if (minion instanceof HeroMinion && ((HeroMinion) minion).isLethalDose(dmg)){
                    minion.addImmune();
                    return true;
                }
            }
            return false;
        }

        @Override
        public Class<? extends Event> triggerEvent() {
            return MinionBeHurtEvent.class;
        }
    }
}

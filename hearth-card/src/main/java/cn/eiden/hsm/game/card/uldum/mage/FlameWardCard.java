package cn.eiden.hsm.game.card.uldum.mage;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.AfterHeroBeAttackEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.uldum.mage.FlameWard;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.MinionObject;
import cn.eiden.hsm.game.minion.Secret;
import cn.eiden.hsm.output.OutputInfo;

/**
 * 火焰结界
 * @author Eiden J.P Zhou
 * @date 2020/6/3 16:20
 */
public class FlameWardCard extends FlameWard {
    @Override
    public Secret createSecret() {
        return new FlameWardSecret();
    }

    static class FlameWardSecret extends AbstractSecret{

        private static final int DMG = 3;

        @Override
        public boolean onSecret(Event event) {
            if (event.getClass() == triggerEvent()){
                AfterHeroBeAttackEvent afterAttackEvent = (AfterHeroBeAttackEvent) event;
                Minion attacker = afterAttackEvent.getAttacker();
                if (attacker instanceof MinionObject){
                    OutputInfo.info("法师奥秘触发:火焰结界");
                    Gamer attackEventOwner = afterAttackEvent.getOwner();
                    final int damage = DMG + attackEventOwner.getEnemy().getGamerSpellDamage();
                    attackEventOwner.getMinions().forEach(e->e.beHurt(damage));
                    attackEventOwner.checkMinion();
                    return true;
                }
            }
            return false;
        }

        @Override
        public Class<? extends Event> triggerEvent() {
            return AfterHeroBeAttackEvent.class;
        }
    }
}

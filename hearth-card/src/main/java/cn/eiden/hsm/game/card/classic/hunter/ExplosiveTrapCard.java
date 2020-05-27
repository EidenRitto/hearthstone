package cn.eiden.hsm.game.card.classic.hunter;

import cn.eiden.hsm.event.AbstractEvent;
import cn.eiden.hsm.event.events.HeroBeAttackEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.expert1.hunter.ExplosiveTrap;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Secret;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/27 16:16
 */
public class ExplosiveTrapCard extends ExplosiveTrap {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        gamer.addSecret(new ExplosiveTrapSecret());
    }

    static class ExplosiveTrapSecret extends AbstractSecret {
        private static final int dmg = 2;

        @Override
        public boolean onSecret(AbstractEvent event) {
            if (event.getClass() == triggerEvent()) {
                HeroBeAttackEvent heroBeAttackEvent = (HeroBeAttackEvent) event;
                Minion attacker = heroBeAttackEvent.getAttacker();
                if (getOwner().isFriend(attacker)) {
                    return false;
                }
                for (Minion minion : getOwner().getEnemy().getMinions()) {
                    minion.beHurt(dmg + getOwner().getGamerSpellDamage());
                }
                return true;
            }
            return false;
        }

        @Override
        public Class<? extends AbstractEvent> triggerEvent() {
            return HeroBeAttackEvent.class;
        }
    }
}

package cn.eiden.hsm.game.card.classic.hunter;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.HeroBeAttackEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.card.defs.expert1.hunter.ExplosiveTrap;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/27 16:16
 */
public class ExplosiveTrapCard extends ExplosiveTrap {
    @Override
    public void magicEffect(Gamer gamer, Minion target) {
        ExplosiveTrapSecret trapSecret = new ExplosiveTrapSecret();
        gamer.addSecret(trapSecret);
    }

    static class ExplosiveTrapSecret extends AbstractSecret {
        private static final int dmg = 2;

        @Override
        public boolean onSecret(Event event) {
            if (event.getClass() == triggerEvent()) {
                HeroBeAttackEvent heroBeAttackEvent = (HeroBeAttackEvent) event;
                Minion attacker = heroBeAttackEvent.getAttacker();
                if (getOwner().isFriend(attacker)) {
                    return false;
                }
                OutputInfo.info("猎人奥秘触发：爆炸陷阱！");
                int damage = dmg + getOwner().getGamerSpellDamage();
                for (Minion minion : getOwner().getEnemy().getMinions()) {
                    minion.beHurt(damage);
                }
                getOwner().getEnemy().getHero().beHurt(damage);
                getOwner().getEnemy().checkMinion();
                return true;
            }
            return false;
        }

        @Override
        public Class<? extends Event> triggerEvent() {
            return HeroBeAttackEvent.class;
        }
    }
}

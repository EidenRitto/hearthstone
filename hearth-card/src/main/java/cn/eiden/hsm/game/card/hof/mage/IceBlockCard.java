package cn.eiden.hsm.game.card.hof.mage;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.MinionBeHurtEvent;
import cn.eiden.hsm.game.card.defs.hof.mage.IceBlock;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.game.minion.Secret;
import cn.eiden.hsm.game.minion.hero.HeroMinion;
import cn.eiden.hsm.game.rule.rules.ImmuneUntilEndTurnRule;
import cn.eiden.hsm.output.OutputInfo;

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
                    event.getOwner().addRule(new ImmuneUntilEndTurnRule());
                    event.getOwner().printPublicQueue("法师奥秘触发:寒冰屏障");
                    event.getOwner().printPublicQueue(String.format("%s获得免疫",event.getOwner().getHero().getMinionName()));
                    event.getOwner().printPublicQueue("打得不错");
                    event.getOwner().printPublicQueue("我的魔法会把你撕成碎片");
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

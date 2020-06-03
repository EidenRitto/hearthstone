package cn.eiden.hsm.game.card.classic.mage;

import cn.eiden.hsm.event.Event;
import cn.eiden.hsm.event.events.UseSpellCardFromHandEvent;
import cn.eiden.hsm.game.card.defs.expert1.mage.Counterspell;
import cn.eiden.hsm.game.minion.AbstractSecret;
import cn.eiden.hsm.game.minion.Secret;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/3 16:03
 */
public class CounterspellCard extends Counterspell {
    @Override
    public Secret createSecret() {
        return new CounterspellSecret();
    }

    static class CounterspellSecret extends AbstractSecret{

        @Override
        public boolean onSecret(Event event) {
            if (event.getClass() == triggerEvent()){
                UseSpellCardFromHandEvent spellEvent = (UseSpellCardFromHandEvent) event;
                spellEvent.getMagicCard().setCounter(true);
                OutputInfo.info("法师奥秘触发:法术反制");
                OutputInfo.info("法术<%s>无效",spellEvent.getMagicCard().getCardName());
                return true;
            }
            return false;
        }

        @Override
        public Class<? extends Event> triggerEvent() {
            return UseSpellCardFromHandEvent.class;
        }
    }
}

package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.AddMinionEvent;
import cn.eiden.hsm.game.keyword.Charge;
import cn.eiden.hsm.game.keyword.SpellDamage;
import cn.eiden.hsm.game.keyword.Taunt;
import cn.eiden.hsm.game.minion.Minion;
import cn.eiden.hsm.listener.HearthListener;

/**
 * @author Eiden J.P Zhou
 * @date 2020/3/19 15:59
 */
public class SimpleKeyWordListener implements HearthListener {
    @EventHandler
    public void onEventAddTaunt(AddMinionEvent event)
    {
        Minion minion = event.getMinion();
        if (minion instanceof Taunt){
            minion.addTaunt();
        }
        if (minion instanceof Charge){
            minion.addCharge();
        }
        if (minion instanceof SpellDamage){
            minion.setSpellPower(((SpellDamage) minion).spellDamageAdd());
        }
    }
}

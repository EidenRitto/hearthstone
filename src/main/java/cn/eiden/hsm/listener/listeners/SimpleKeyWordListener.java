package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.UseMinionCardFromHandEvent;
import cn.eiden.hsm.game.keyword.Charge;
import cn.eiden.hsm.game.keyword.SpellDamage;
import cn.eiden.hsm.game.keyword.Taunt;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.listener.HearthListener;

/**
 * @author 周晋平
 * @date 2020/3/19 15:59
 */
public class SimpleKeyWordListener implements HearthListener {
    @EventHandler
    public void onEventAddTaunt(UseMinionCardFromHandEvent event)
    {
        Minion minion = event.getMinionObject();
        if (minion instanceof Taunt){
            minion.addTaunt();
        }
    }

    @EventHandler
    public void onEventAddCharge(UseMinionCardFromHandEvent event)
    {
        Minion minion = event.getMinionObject();
        if (minion instanceof Charge){
            minion.addCharge();
        }
    }

    @EventHandler
    public void onEventAddSpellDamage(UseMinionCardFromHandEvent event)
    {
        Minion minion = event.getMinionObject();
        if (minion instanceof SpellDamage){
            minion.setSpellDamage(((SpellDamage) minion).spellDamageAdd());
        }
    }
}

package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.BattlefieldChangeEvent;
import cn.eiden.hsm.game.keyword.Charge;
import cn.eiden.hsm.game.keyword.SpellDamage;
import cn.eiden.hsm.game.keyword.Taunt;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.listener.HearthListener;

import java.util.List;

/**
 * @author 周晋平
 * @date 2020/3/19 15:59
 */
public class SimpleKeyWordListener implements HearthListener {
    @EventHandler
    public void onEventAddTaunt(BattlefieldChangeEvent event)
    {
        List<Minion> minions = event.getOwner().getMinions();
        for (Minion minion : minions) {
            if (minion.isSilence()){
                continue;
            }
            if (minion instanceof Taunt){
                minion.addTaunt();
            }
            if (minion instanceof Charge){
                minion.addCharge();
            }
            if (minion instanceof SpellDamage){
                minion.setSpellDamage(((SpellDamage) minion).spellDamageAdd());
            }
        }
    }
}

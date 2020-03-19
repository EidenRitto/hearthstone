package cn.eiden.hsm.listener.listeners;

import cn.eiden.hsm.annotation.EventHandler;
import cn.eiden.hsm.event.events.BattlefieldChangeEvent;
import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.keyword.BuffHole;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.game.objct.Minion;
import cn.eiden.hsm.listener.HearthListener;

import java.util.List;

/**
 * 刷新光环效果监听
 * @author Eiden J.P Zhou
 * @date 2020/3/16 17:50
 */
public class BuffHoleListener implements HearthListener {

    @EventHandler
    public void onEvent(BattlefieldChangeEvent event){
        Gamer owner = event.getOwner();
        List<Minion> minions = owner.getMinions();
        for (Minion minion : minions) {
            if (minion instanceof BuffHole){
                BuffHole buffHoleMinion = (BuffHole) minion;
                buffHoleMinion.doBuffHole();
            }
        }
    }
}

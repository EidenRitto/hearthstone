package cn.eiden.hsm.game.card.classic;

import cn.eiden.hsm.enums.Race;
import cn.eiden.hsm.game.card.defs.expert1.neutral.SouthseaCaptain;
import cn.eiden.hsm.game.keyword.Aura;
import cn.eiden.hsm.game.minion.Minion;

import java.util.ArrayList;
import java.util.List;

/**
 * 南海船长
 * @author Eiden J.P Zhou
 * @date 2020/5/21 15:12
 */
public class SouthseaCaptainCard extends SouthseaCaptain{

    @Override
    protected Aura selfAura() {
        return new SelfAura();
    }

    static class SelfAura implements Aura{
        /**被buff的随从*/
        List<Minion> buffedMinions = new ArrayList<>();
        @Override
        public void doAura(Minion thisMinion) {
            List<Minion> minions = thisMinion.getOwner().getMinions();
            for (Minion minion : minions) {
                if (buffedMinions.contains(minion)){
                    continue;
                }
                if (minion.getRace() == Race.PIRATE && !minion.equals(thisMinion) ){
                    minion.addAttack(1L);
                    minion.addHealthLimit(1L);
                    buffedMinions.add(minion);
                }
            }
        }
    }

}

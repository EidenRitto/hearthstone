package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.objct.Minion;
import lombok.Setter;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/23 17:32
 */
@Setter
public class AtkOrder implements Order {
    private Integer sourceIndex;
    private Integer targetIndex;
    private Gamer gamer;

    @Override
    public void execute() {
        Minion attacker;
        Minion beAttacker;
        if (sourceIndex == OrderConstant.TARGET_HERO_INDEX) {
            attacker = gamer.getHero();
        } else {
            attacker = gamer.getMinion(sourceIndex);
        }
        if (targetIndex == OrderConstant.TARGET_HERO_INDEX) {
            beAttacker = gamer.getEnemy().getHero();
        } else {
            beAttacker = gamer.getEnemy().getMinion(sourceIndex);
        }
        attacker.attack(beAttacker);
    }

    @Override
    public boolean isComplete() {
        return sourceIndex != null && targetIndex != null && gamer != null;
    }

    private void resetOrder() {
        this.targetIndex = null;
        this.sourceIndex = null;
        this.gamer = null;
    }
}

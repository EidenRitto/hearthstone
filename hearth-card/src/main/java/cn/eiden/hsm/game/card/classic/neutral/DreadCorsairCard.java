package cn.eiden.hsm.game.card.classic.neutral;

import cn.eiden.hsm.game.card.defs.expert1.neutral.DreadCorsair;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/21 16:43
 */
public class DreadCorsairCard extends DreadCorsair {

    @Override
    public int getCost() {
        int cost = super.getCost();
        if (getOwner().getHero().hasWeapon()){
            cost = (int) (cost - getOwner().getHero().getWeapon().getWeaponAttack());
            return Math.max(0,cost);
        }else {
            return cost;
        }
    }
}

package cn.eiden.hsm.game.card.base;


import cn.eiden.hsm.game.card.defs.expert1.neutral.LeperGnome;
import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.GeneralItem;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 1.0
 *  麻风侏儒卡牌
 * */

public class LeperGnomeCard extends LeperGnome {

    @Override
    protected DeathRattle selfDeathRattle() {
        return new MyDeathRattle();
    }

    static public class MyDeathRattle implements DeathRattle{
        @Override
        public void doDeathRattle(GeneralItem generalItem) {
            generalItem.getOwner().getEnemy().getHero().beHurt(2);
        }
    }
}

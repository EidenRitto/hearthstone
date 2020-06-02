package cn.eiden.hsm.game.card.base;


import cn.eiden.hsm.game.card.defs.expert1.neutral.LeperGnome;
import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.GeneralItem;
import cn.eiden.hsm.game.minion.Minion;

/**
 * 麻风侏儒卡牌
 * @author : Eiden J.P Zhou
 * @date : 2018/9/13
 * @version : 3.0
 *
 * */
public class LeperGnomeCard extends LeperGnome {

    @Override
    protected DeathRattle selfDeathRattle() {
        return new MyDeathRattle();
    }

    public static class MyDeathRattle implements DeathRattle{
        @Override
        public void doDeathRattle(GeneralItem generalItem) {
            if (generalItem instanceof Minion){
                Minion minion = (Minion) generalItem;
                generalItem.getOwner().getEnemy().getHero().beHurt(minion,2);
            }
        }
    }
}

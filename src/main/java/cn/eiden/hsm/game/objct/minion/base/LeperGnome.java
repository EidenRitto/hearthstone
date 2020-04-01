package cn.eiden.hsm.game.objct.minion.base;

import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.objct.AbstractMinionObject;
import cn.eiden.hsm.output.OutputInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  麻风侏儒 111
 * */
@Slf4j
public class LeperGnome extends AbstractMinionObject implements DeathRattle {
    private static final String SERVANT_NAME = "麻风侏儒";
    private static final Long HEALTH = 1L;
    private static final Long ATTACK = 1L;

    @Override
    public void doDeathRattle(){
        //如果是敌人的随从，我方掉血，反之对方掉血
        OutputInfo.info(this.getMinionName()+"触发亡语");
        this.owner.getEnemy().getHero().beHurt(2);

    }

    public LeperGnome() {
        super(SERVANT_NAME,HEALTH, ATTACK);
    }
}

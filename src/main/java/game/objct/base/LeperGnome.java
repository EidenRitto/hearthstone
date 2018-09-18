package game.objct.base;

import game.Gamer;
import game.objct.GameObject;
import game.objct.ServantObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description : 麻风侏儒 111
 * */
public class LeperGnome extends ServantObject{
    private static final String SERVANT_NAME = "麻风侏儒";
    private static final Long HEALTH = 1L;
    private static final Long ATTACK = 1L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    @Override
    public void doDeathVoice(Gamer self){
        //如果是敌人的随从，我方掉血，反之对方掉血

        self.getEnemy().beHurt(2);

    }

    public LeperGnome() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        isDeathVoice = true;
    }
}

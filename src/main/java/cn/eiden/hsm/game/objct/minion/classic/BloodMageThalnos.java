package cn.eiden.hsm.game.objct.minion.classic;

import cn.eiden.hsm.game.keyword.DeathRattle;
import cn.eiden.hsm.game.objct.AbstractMinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/18
 */
public class BloodMageThalnos extends AbstractMinionObject implements DeathRattle {
    private static final String SERVANT_NAME = "血法师萨尔诺斯";
    private static final Long HEALTH = 1L;
    private static final Long ATTACK = 1L;
    private static final boolean ATTACK_STATE = false;
    private static final int ATTACK_TIME = 1;

    /**抽卡数量*/
    private static final int DRAW_CARD_NUMBER = 1;
    /**法术强度*/
    private static final int SPELL_DAMAGE = 1;

    public BloodMageThalnos() {
        super(SERVANT_NAME,HEALTH, HEALTH, ATTACK, ATTACK_STATE, ATTACK_TIME);
        spellDamage = SPELL_DAMAGE;
    }

    @Override
    public void doDeathRattle(){
        this.owner.drawCard(DRAW_CARD_NUMBER);
    }
}

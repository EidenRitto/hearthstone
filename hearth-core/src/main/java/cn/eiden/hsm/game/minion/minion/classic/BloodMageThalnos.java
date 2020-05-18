package cn.eiden.hsm.game.minion.minion.classic;

import cn.eiden.hsm.game.keyword.SpellDamage;
import cn.eiden.hsm.game.minion.MinionObject;

/**
 * @author : Eiden J.P Zhou
 * @date 2018/9/18
 */
public class BloodMageThalnos extends MinionObject implements SpellDamage {
    private static final String SERVANT_NAME = "血法师萨尔诺斯";
    private static final Long HEALTH = 1L;
    private static final Long ATTACK = 1L;

    /**抽卡数量*/
    private static final int DRAW_CARD_NUMBER = 1;
    /**法术强度*/
    private static final int SPELL_DAMAGE = 1;

    public BloodMageThalnos() {
        super(SERVANT_NAME,HEALTH, ATTACK);
    }

    @Override
    public int spellDamageAdd() {
        return SPELL_DAMAGE;
    }
}

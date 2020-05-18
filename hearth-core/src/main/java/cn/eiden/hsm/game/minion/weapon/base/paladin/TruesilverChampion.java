package cn.eiden.hsm.game.minion.weapon.base.paladin;

import cn.eiden.hsm.game.Gamer;
import cn.eiden.hsm.game.minion.WeaponObject;
import cn.eiden.hsm.output.OutputInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 *
 *
 */
@Slf4j
public class TruesilverChampion extends WeaponObject {
    private static final String WEAPON_NAME = "真银圣剑";
    private static final Long ATTACK = 4L;
    private static final Long DURABLE = 2L;
    /**攻击时恢复的生命值*/
    private static final Long RECOVERY_HEALTH = 2L;

    @Override
    public void whenAttackDo(Gamer gamer){
        OutputInfo.info("真银圣剑发动特效:");
        gamer.getHero().recoveryHp(RECOVERY_HEALTH);
    }

    public TruesilverChampion() {
        super(WEAPON_NAME,ATTACK, DURABLE);
    }
}

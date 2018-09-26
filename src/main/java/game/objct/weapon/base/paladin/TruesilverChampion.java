package game.objct.weapon.base.paladin;

import game.objct.WeaponObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 * @Description:
 * @Modified By:
 */
public class TruesilverChampion extends WeaponObject {
    private static final String WEAPON_NAME = "真银圣剑";
    private static final Long ATTACK = 4L;
    private static final Long DURABLE = 2L;

    @Override
    public void whenAttackDo(){

    }

    public TruesilverChampion() {
        super(WEAPON_NAME,ATTACK, DURABLE);
    }
}

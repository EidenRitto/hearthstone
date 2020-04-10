package cn.eiden.hsm.game.objct.weapon.base.warrior;

import cn.eiden.hsm.game.objct.WeaponObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 *
 *
 */
public class FieryWarAxe extends WeaponObject {
    private static final String WEAPON_NAME = "炙炎战斧";
    private static final Long ATTACK = 3L;
    private static final Long DURABLE = 2L;

    public FieryWarAxe() {
        super(WEAPON_NAME,ATTACK, DURABLE);
    }
}
package cn.eiden.hsm.game.card.base.warrior;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.weapon.base.warrior.FieryWarAxe;
import cn.eiden.hsm.game.card.AbstractWeaponCard;
import cn.eiden.hsm.game.tags.Version;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 *  炙炎战斧
 *
 */
@Tags(profession = Profession.Warrior,version = Version.BASE)
public class FieryWarAxeCard extends AbstractWeaponCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "炙炎战斧";
    private static final Long ATTACK_VALUE = 3L;
    private static final Long DURABLE = 2L;


    public FieryWarAxeCard() {
        super(COST, DESCRIPTION, CARD_NAME, Profession.Warrior, DURABLE, ATTACK_VALUE,new FieryWarAxe());
    }
}

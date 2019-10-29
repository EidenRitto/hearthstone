package cn.eiden.hsm.game.card.base.paladin;

import cn.eiden.hsm.game.objct.weapon.base.paladin.TruesilverChampion;
import cn.eiden.hsm.game.card.WeaponCard;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 * @Description:
 * @Modified By:
 */
public class TruesilverChampionCard extends WeaponCard {
    private static final int COST = 4;
    private static final String DESCRIPTION = "英雄攻击时恢复2点生命值";
    private static final String CARD_NAME = "真银圣剑";
    private static final Long ATTACK_VALUE = 4L;
    private static final Long DURABLE = 2L;


    public TruesilverChampionCard() {
        super(COST, DESCRIPTION, CARD_NAME, DURABLE, ATTACK_VALUE,new TruesilverChampion());
    }
}

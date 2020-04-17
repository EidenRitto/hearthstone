package cn.eiden.hsm.game.card.base.paladin;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.objct.WeaponObject;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.weapon.base.paladin.TruesilverChampion;
import cn.eiden.hsm.game.card.AbstractWeaponCard;
import cn.eiden.hsm.game.tags.Version;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 *
 *
 */
@Tags(profession = Profession.Paladin,version = Version.BASE)
public class TruesilverChampionCard extends AbstractWeaponCard {
    private static final int COST = 4;
    private static final String DESCRIPTION = "英雄攻击时恢复2点生命值";
    private static final String CARD_NAME = "真银圣剑";
    private static final Long ATTACK_VALUE = 4L;
    private static final Long DURABLE = 2L;

    public TruesilverChampionCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, CardClass cardClass, CardType cardType, Rarity rarity, long atk, long durability) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity, atk, durability);
    }


    @Override
    public WeaponObject createWeapon() {
        return null;
    }
}

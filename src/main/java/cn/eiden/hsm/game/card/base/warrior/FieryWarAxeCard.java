package cn.eiden.hsm.game.card.base.warrior;

import cn.eiden.hsm.annotation.Tags;
import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.minion.WeaponObject;
import cn.eiden.hsm.game.card.AbstractWeaponCard;


/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 *  炙炎战斧
 *
 */
@Tags(cardClass = CardClass.WARRIOR, cardSet = CardSet.CORE)
public class FieryWarAxeCard extends AbstractWeaponCard {
    private static final int COST = 3;
    private static final String DESCRIPTION = "";
    private static final String CARD_NAME = "炙炎战斧";
    private static final Long ATTACK_VALUE = 3L;
    private static final Long DURABLE = 2L;

    public FieryWarAxeCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, cn.eiden.hsm.enums.CardClass cardClass, CardType cardType, Rarity rarity, long atk, long durability) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity, atk, durability);
    }


    @Override
    public WeaponObject createWeapon() {
        return null;
    }
}

package cn.eiden.hsm.game.card;


import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.minion.WeaponObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/26
 */
@Setter
@Getter
public abstract class AbstractWeaponCard extends AbstractCard {
    /**武器攻击力*/
    private long atk;
    /**耐久度*/
    private long durability;

    public AbstractWeaponCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, CardClass cardClass, CardType cardType, Rarity rarity, long atk, long durability) {
        super(cardName, cost, description, id, cardId, cardSet, cardClass, cardType, rarity);
        this.atk = atk;
        this.durability = durability;
    }

    /**
     * 组合优于继承
     * 创建卡牌对应的武器
     * @return 卡牌对应的武器
     * */
    public abstract WeaponObject createWeapon();
}

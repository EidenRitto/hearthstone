package cn.eiden.hsm.dbdata;

import cn.eiden.hsm.enums.*;
import lombok.Data;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/14 10:09
 */
@Data
public class CardInfo {
    /**名称*/
    private String cardName;
    /**id*/
    private String cardId;
    private String id;
    /**中文名*/
    private String cardCnName;
    /**版本*/
    private CardSet cardSet;
    /**职业*/
    private CardClass cardClass;
    /**类型*/
    private CardType cardType;
    /**稀有度*/
    private Rarity rarity;
    /**文本描述*/
    private String cardText;
    private int cost;

    private int atk;
    private int health;
    private int charge;
    /**种族*/
    private Race race;
    /**武器耐久*/
    private int durability;

    private int taunt;

    public CardInfo() {
        this.rarity = Rarity.INVALID;
        this.cardClass = CardClass.INVALID;
        this.race = Race.INVALID;
    }
}

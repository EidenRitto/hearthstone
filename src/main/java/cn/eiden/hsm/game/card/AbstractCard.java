package cn.eiden.hsm.game.card;


import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.GameObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 2.0
 *
 * */
public abstract class AbstractCard extends GameObject implements Card{
    private String cardName;
    private int cost;
    private String description;

    /**id*/
    private String id;
    /**cardId*/
    private String cardId;
    /**版本*/
    private CardSet cardSet;
    /**职业*/
    private cn.eiden.hsm.enums.CardClass cardClass;
    /**类型*/
    private CardType cardType;
    /**稀有度*/
    private Rarity rarity;

    public AbstractCard() {
    }

    public AbstractCard(int cost, String description, String cardName, CardClass cardClass) {
        this.cost = cost;
        this.description = description;
        this.cardName = cardName;
        this.cardClass = cardClass;
    }

    public AbstractCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, cn.eiden.hsm.enums.CardClass cardClass, CardType cardType, Rarity rarity) {
        this.cardName = cardName;
        this.cost = cost;
        this.description = description;
        this.id = id;
        this.cardId = cardId;
        this.cardSet = cardSet;
        this.cardClass = cardClass;
        this.cardType = cardType;
        this.rarity = rarity;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCardName() {
        return cardName;
    }

    @Override
    public CardClass getCardClass() {
        return cardClass;
    }
}

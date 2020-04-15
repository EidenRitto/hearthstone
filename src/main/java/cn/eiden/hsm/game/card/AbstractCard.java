package cn.eiden.hsm.game.card;


import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.GameObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *
 * */
public abstract class AbstractCard extends GameObject implements Card{
    private String cardName;
    private int cost;
    private String description;
    private Profession profession;

    /**id*/
    private String cardId;
    /**版本*/
    private CardSet cardSet;
    /**职业*/
    private CardClass cardClass;
    /**类型*/
    private CardType cardType;
    /**稀有度*/
    private Rarity rarity;

    public AbstractCard() {
    }

    public AbstractCard(int cost, String description,String cardName,Profession profession) {
        this.cost = cost;
        this.description = description;
        this.cardName = cardName;
        this.profession = profession;
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
    public Profession getProfession() {
        return profession;
    }
}

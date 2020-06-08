package cn.eiden.hsm.game.card;


import cn.eiden.hsm.enums.CardClass;
import cn.eiden.hsm.enums.CardSet;
import cn.eiden.hsm.enums.CardType;
import cn.eiden.hsm.enums.Rarity;
import cn.eiden.hsm.game.AbstractGeneralItem;

import java.util.List;

/**
 * @author : Eiden J.P Zhou
 * @version : 2.0
 * @date : 2018/9/12
 */
public abstract class AbstractCard extends AbstractGeneralItem implements Card {
    /**
     * 卡牌名称
     */
    private String cardName;
    /**
     * 卡牌费用
     */
    private int cost;
    /**
     * 卡牌描述
     */
    private String description;

    /**
     * id
     */
    private String id;
    /**
     * cardId
     */
    private String cardId;
    /**
     * 版本
     */
    private CardSet cardSet;
    /**
     * 职业
     */
    private CardClass cardClass;
    /**
     * 类型
     */
    private CardType cardType;
    /**
     * 稀有度
     */
    private Rarity rarity;
    /**
     * 过载
     */
    private int overload;

    /**
     * 抉择选项
     */
    private List<Card> chooseOneList;

    private Integer ruleForceCost;

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

    public AbstractCard(String cardName, int cost, String description, String id, String cardId, CardSet cardSet, cn.eiden.hsm.enums.CardClass cardClass, CardType cardType, Rarity rarity, int overload) {
        this.cardName = cardName;
        this.cost = cost;
        this.description = description;
        this.id = id;
        this.cardId = cardId;
        this.cardSet = cardSet;
        this.cardClass = cardClass;
        this.cardType = cardType;
        this.rarity = rarity;
        this.overload = overload;
    }

    @Override
    public int getCost() {
        return ruleForceCost == null ? cost : ruleForceCost;
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

    @Override
    public void setRuleForceCost(Integer ruleForceCost) {
        this.ruleForceCost = ruleForceCost;
    }

    @Override
    public void resetRuleForceCost() {
        this.ruleForceCost = null;
    }

    @Override
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public int getOverload() {
        return this.overload;
    }

    @Override
    public boolean hasChooseOne() {
        return this.chooseOneList != null;
    }

    @Override
    public void addChooseOne(List<Card> options) {
        this.chooseOneList = options;
    }

    @Override
    public List<Card> getOptions() {
        return this.chooseOneList;
    }
}

package cn.eiden.hsm.game.card;


import cn.eiden.hsm.game.objct.GameObject;

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

    public AbstractCard() {
    }

    public AbstractCard(int cost, String description,String cardName) {
        this.cost = cost;
        this.description = description;
        this.cardName = cardName;
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
}

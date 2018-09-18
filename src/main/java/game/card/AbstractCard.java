package game.card;


import game.objct.GameObject;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 * @description :
 * */
public abstract class AbstractCard extends GameObject {
    private String cardName;
    private int cost;
    private String description;

    public abstract void useCard();

    public AbstractCard() {
    }

    public AbstractCard(int cost, String description,String cardName) {
        this.cost = cost;
        this.description = description;
        this.cardName = cardName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}

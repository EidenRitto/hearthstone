package cn.eiden.hsm.game.card;


import cn.eiden.hsm.game.hero.Profession;
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
    private Profession profession;

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

package cn.eiden.hsm.game.card;


import cn.eiden.hsm.enums.*;
import cn.eiden.hsm.game.objct.MinionObject;
import cn.eiden.hsm.game.tags.Profession;
import cn.eiden.hsm.game.objct.Minion;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Eiden J.P Zhou
 * @date : 2018/9/12
 * @version : 1.0
 *  随从卡
 * */
@Setter
@Getter
public abstract class AbstractMinionCard extends AbstractCard {
    /**生命值上限*/
    private Long health;
    /**攻击力*/
    private Long atk;
    /**种族*/
    private Race race;

    public AbstractMinionCard(Long health, Long atk) {
        this.health = health;
        this.atk = atk;
    }

    public AbstractMinionCard(int cost, String description, String cardName, Profession profession, Long health, Long atk) {
        super(cost, description, cardName, profession);
        this.health = health;
        this.atk = atk;
    }

    public AbstractMinionCard(String cardName, int cost, String description, String cardId, CardSet cardSet, CardClass cardClass, CardType cardType, Rarity rarity, Long health, Long atk, Race race) {
        super(cardName, cost, description, cardId, cardSet, cardClass, cardType, rarity);
        this.health = health;
        this.atk = atk;
        this.race = race;
    }

    public abstract MinionObject createMinion();
}
